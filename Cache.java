import java.util.*;


public class Cache
{

	/**
	 * The <Cache> object is responsible for consulting the current request, 
	 * checking if said request is present, and adjusting itself according 
	 * to the specified caching <policy>
	 */
	
	 //Where the addresses in the current cache are stored
            public List<CacheEntry> cache_proper;
	 // number of addresses which can occupy cache
	    public int cache_size;
	 // mumber of different addresses to be referenced 
	    public int address_capacity;
	 // how "long", or how many itterations have been made
	    public int current_time;
	 // which replacement policy is to be used
	    public String policy;

	/**
	 * <Cache> constructor, accepts {cache size, policy, number of addresses}
	 */

	  public Cache(int cache_size, String policy, int address_capacity)
	    {
		    this.cache_size = cache_size;
		    this.policy = policy;
		    this.address_capacity = address_capacity;

		    this.current_time = 0;
	            cache_proper = new ArrayList<CacheEntry>();

		    int i = 0;
		    while( i < this.cache_size)
		    {
			cache_proper.add(new CacheEntry(-1));
			i++;
		    }
	    }

	   public void iterate_time(){this.current_time++;}

	   public boolean is_in_cache(int request)
	    {
		    boolean present = false;

		    for(CacheEntry entry: this.cache_proper)
		    {
			    if (entry.fetch_address() == request)
			    	{
				     present = true;
				     entry.iterate_popularity();
				     entry.used_at_time(this.current_time);
				     break;
				}
		    }

		    return present;
		 
	    }// is_in_cache

	   public void replace (int new_address)
	    {
			
		CacheEntry new_entry = new CacheEntry(new_address);

		    switch(this.policy.toLowerCase())
		    {

			    case "random":
				    Random r = new Random();
			            int random_index = r.nextInt(this.cache_size);

				    if(random_index >= this.cache_proper.size())
					  	cache_proper.add(new_entry);   
				    else
				    	this.cache_proper.set(random_index, new_entry);

				    break;

			    case "lru": 
				    //the longest time since a request could be made
				    int longest_time = -1;
				    int lru_index = 0;

				    for(int index = 0; index < this.cache_size; index++)
				    {
					int time_at_index = this.cache_proper.get(index).time_since(current_time);
					
					if(time_at_index > longest_time)
					{ 
						longest_time = time_at_index;
						lru_index = index;
						
					}
				    }

				    if (this.cache_proper.isEmpty())
					  cache_proper.add(new_entry);
					   
				    else
				    	this.cache_proper.set(lru_index, new_entry);
				    break;

			    case "lfu":
				   int index_least_popular = 0;
				   int lowest_popularity = Integer.MAX_VALUE;

				   for(int index = 0; index < this.cache_size; index++)
					{

					 int popularity_at_index = this.cache_proper.get(index).fetch_popularity();

					 if(popularity_at_index < lowest_popularity)
					 	{
						 	lowest_popularity = popularity_at_index;
						 	index_least_popular = index;
					 	}
					}

				    if (this.cache_proper.isEmpty())
					  cache_proper.add(new_entry);
					   
				    else
				    	this.cache_proper.set(index_least_popular, new_entry);		   
				break;

			    case "fifo":

				     // the time of the earliest addition
				     int earliest_addition = this.current_time;
				     int index_of_first = 0;
				     for (int index = 0; index < this.cache_size; index++)
				     {
					int index_time_of_addition = 
							this.cache_proper.get(index).time_added;
					
					if(index_time_of_addition < earliest_addition)
					{
						earliest_addition = index_time_of_addition;
						index_of_first = index;
					}	
				     }

	  			if (this.cache_proper.isEmpty())
					this.cache_proper.add(new_entry);
				else
			     	    this.cache_proper.set(index_of_first, new_entry);    
				break;

		    }

	    }

		
}

class CacheEntry
{
	public int address;
	public int time_used;
	public int time_added;
	public int popularity;

	public CacheEntry(int address)
	{
		this.address = address;
		this.popularity = 0;
		this.time_used = 0;
		this.time_added = 0;
	}


	public void iterate_popularity()
	{this.popularity++;}

	public void used_at_time(int time)
	{this.time_used = time;}

	public int fetch_address()
	{return this.address;}

	public int fetch_popularity()
	{return this.popularity;}

	public void set_time_added(int time)
	{this.time_added = time;}

	public int time_since(int current_time)
	{return (current_time-this.time_used);}
}




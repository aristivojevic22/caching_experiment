public class Simulation
{
	
	public Simulation(char distribution, int cache_size, String replacement_policy)
		{ Experiment e = new Experiment(distribution, cache_size, replacement_policy);}


}// Simulation

 class Experiment
{

	/** experiment constructor: 
	 * 	instantiated instances of <Experiment>
	 * 	have dependent variables in the form of 
	 * 	constructor perameters, and a <fetch_results()>
	 * 	method which returns the specific datum reqested
	 * 	 by the <Simulation>
	 */

	RequestChain requested_data;
	Cache 	     cache;
	String replacement_policy;
	int request_size;
	int cache_size;
	int requests_to_neglect;
	char distribution;

	public int hits;


	public Experiment(char distribution, 
			  int cache_size,
			  int address_capacity,
			  int request_size,
			  int requests_to_neglect,
			  String replacement_policy)
	{
		this.distribution = distribution;
		this.cache_size = cache_size;
		this.request_size = request_size;
		this.replacement_policy = replacement_policy;

		requested_data  = new RequestChain(address_capacity,
						   request_size,
						   distribution);

		cache           = new Cache(cache_size, replacement_policy, address_capacity);
		this.hits = 0;
		run();

	}// Experiment() 

	/** default experiment perameters*/

	public Experiment(char distribution, 
			  int cache_size, 
			  String replacement_policy)
	{
		this(distribution, cache_size, 1000, 1000000, 1000, replacement_policy);
	}

	public void run()
	{
		int iteration = 0;
		while(iteration < this.request_size)
		
		{
			int current_request = this.requested_data.request_at(iteration).requested_address;
			
			//System.out.println("iteration:" + iteration );	

			if(this.cache.is_in_cache(current_request))
			{
				//System.out.println(current_request + "---> 1" + "\n");

				// to neglect the first 10,000 requests in calculating hitrate
				if(iteration >= 10000)
					this.hits++;
			}

			else 	
			{  	this.cache.replace(current_request); 
				//System.out.println(current_request + "---> 0" + "\n");
			}
			
			this.cache.iterate_time();
			
			iteration++;
		}

			return_summary();	


	}

	public void return_summary()
	{
		
		System.out.println("distribution: " + this.distribution);	
		System.out.println("cache size: " + this.cache_size);
		System.out.println("replacement policy: " + this.replacement_policy );
		System.out.println("total requests: " + this.request_size);
		System.out.println("total hits: " + this.hits);
		System.out.println("total misses: " + (this.request_size-this.hits));
		System.out.println("frequency of hits: " + (double)hits/request_size);
	}
}// Experiment

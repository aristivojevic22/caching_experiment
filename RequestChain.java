import java.io.*;
import java.util.*;

public class RequestChain
{
	public List<Request> requests;
	public int request_length;
	public int address_capacity;

	/** default constructor for <RequestChain>*/

	public RequestChain (int address_capacity,
		       		int request_length,	
			        char distribution_type )
	{
		requests = new ArrayList<Request>();
		this.request_length = request_length;
		this.address_capacity=address_capacity;

		switch(distribution_type)
		{
			case 'U':
				populate_uniform_requests();	
				break;
			case 'Z':
				populate_zipf_requests();
				break;
			case 'D':
				populate_dependent_requests();
				break;
		}

	}// RequestChain

	/** overloaded constructor for default values */
	public RequestChain ()
	{ this(1000,100000,'U');}
	
	public String to_string()
	{
		String to_return = "{ ";

		for(Request r : this.requests)
		{
			to_return += 
			"[ " + r.fetch_index() + ", " + r.fetch_address() + " ]";
		}

		return to_return +" }";

	}// to string


	public Request request_at(int index)
	{ return this.requests.get(index); }


	public void populate_uniform_requests()
	{	
		UniformDistribution uniform_distribution =
			new UniformDistribution(this.address_capacity);
		
		for(int i = 0; i < this.request_length; i++)
		{
			Request request_i = 
				new Request(i,uniform_distribution.generateRequest());	
		       requests.add(request_i);	
		}

	}// populate uniformly distributed requests

	public void populate_zipf_requests()
	{
		ZipfDistribution zipf_distribution = 
			new ZipfDistribution(this.address_capacity);
		
		for(int i = 0; i < this.request_length; i++)
		{
			Request request_i = 
				new Request(i, zipf_distribution.generateRequest());
			requests.add(request_i);
		}

	}// populate zipf distributed requests 
	
	public void populate_dependent_requests()
	{
		






	}// populate dependent requests

}// Requests

class Request
{	
	/**
	 * each instance of request contains a
	 * 	<index,requested_value>
	 * which are retrievable and unchangable	
	*/

	final int index;
	final int requested_address;

 	public Request(int index, int requested_address)
	{
		this.index=index;
		this.requested_address = requested_address;
	}//Request

	public int fetch_address(){return this.requested_address;}
	public int fetch_index  (){return this.index;            }

}// Request


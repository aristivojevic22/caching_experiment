import java.util.*;
import java.lang.System.*;
import java.io.*;

public class UniformDistribution
{	
	/**
	 * <UniformDistribution> is responsible for generating requests 
	 * sampled uniformly from <address_capacity>;
	 */
	
	//number of RAM addresses from which each request is sampled
	public int address_capacity;	
	// seed for pseudo random number; for generating identical request chains
	public long seed;

	public UniformDistribution(int address_capacity)
	{
		this.address_capacity = address_capacity;
		this.seed = System.currentTimeMillis();
	}//UniformDistribution I

	public UniformDistribution(int address_capacity, long seed)
	{
		this.address_capacity = address_capacity;
		this.seed = seed;

	}// UniformDistribution I

	public int generateRequest()
	{
		Random random_ = new Random();
		return random_.nextInt(this.address_capacity);
	}// generateRequest()
	
	public int[] generateRequests(int request_length)
	{
		int[] array_of_requests = new int[request_length];
		
		for(int i = 0; i < request_length; i++)
			{array_of_requests[i]= generateRequest();}
	
		return array_of_requests;
	}// generateRequest()

}// Uniform Distribution

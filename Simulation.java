public class Simulation
{


	public Simulation( )




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
	char replacement_policy;
	int request_size;
	int chache_size;
	int requests_to_neglect;


	public Experiment(char distribution, 
			  int chache_size,
			  int address_capacity,
			  int request_size,
			  int requests_to_neglect,
			  char replacement_policy)
	{
		requested_data  = new RequestChain(address_capacity,
						   request_size,
						   distribution);



	}// Experiment() 

	/** default experiment perameters*/

	public Experiment(char distribution, 
			int chache_size, 
			char replacement_policy)
	{this(distribution, cache_size, 1000000, 10000, replacement_policy;}


}// Experiment

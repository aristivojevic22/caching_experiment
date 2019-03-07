import java.util.*;
public class TestingClass
{
	public static void main(String[] args)
	{
		//test_zipf();
		test_requests();
	}	
	
	private static void test_zipf()
	{
		ZipfDistribution z = new ZipfDistribution(1000);
		Map<Integer, Integer> map = new HashMap<>();

		int[] reqs = new int[100000];
		for(int i = 0; i < 100000; i ++)
		 {
			int req = z.generateRequest();
			reqs[i] = req;
		 } 

		for (int i = 0; i < reqs.length; i++) 
		 {
        		Integer count = map.getOrDefault(reqs[i], 0);
        		map.put(reqs[i], count + 1);
		 }

    			map.forEach((key, value) -> { System.out.println(key + 
								" -> " + value);});
	}//test zipf	

	private static void test_requests()
	{
		RequestChain request = new RequestChain(1000,100,'U');
		System.out.println(request);	
		for(int i = 0; i < request.requests.size(); i++)
		{
			System.out.println(request.chain_to_string());
		}
	}
}

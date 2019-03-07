import java.util.Random;

public class ZipfDistribution
{

/**
 * D ~ Zipf(a), such that a = 1; 
 * and so Pd(i) = 1/i/(sum from j = 0, <adress_capacity> of 1/j)
 */

private Random random_ = new Random();
public int address_capacity;
public int skew;
public double zeta_value;

/** <ZipfDistribution> accepts the number of memory locations and the scalling factor, <skew>, which 
 *  scales appropriately;
 *  the <zeta_value> is the value assumed by the zeta function given the <address_capacity> and <skew>; 
 */

public ZipfDistribution(int address_capacity, int skew)
{
	this.address_capacity = address_capacity;
	this.skew = skew;

	for (int i = 1; i < address_capacity; i++)
	{
		double denominator = Math.pow(i, this.skew);
		this.zeta_value += (1/denominator);
	}

}// ZipfDistribution()

/**
 * overloaded constructor with default <skew> of <1>;
 */ 

public ZipfDistribution(int address_capacity)
	{ this(address_capacity,1); }


/**
 * <generateRequest()> returns an <address> location distributed according to the zipf distribution;
 */

public int generateRequest()
{
	int address;
	double frequency = 0;
	double random_value;

	address = random_.nextInt(address_capacity + 1);
	frequency = (1/Math.pow(address,this.skew)) / this.zeta_value;
	random_value = random_.nextDouble();

	while(!(random_value < frequency))
		{
			 address = random_.nextInt(address_capacity);
			 frequency = (1/Math.pow(address, this.skew))/this.zeta_value;
			 random_value = random_.nextDouble();
		}
	return address;

}// generateRequest

}// Zipf Distribution

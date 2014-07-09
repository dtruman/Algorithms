import java.util.Iterator;


public class PrimeIterator implements Iterator<Integer>
{
	boolean[] isPrime;
	int maxNeeded;
	int currentPrime;
	
	public PrimeIterator(int max)
	{
		if(max<2)
		{
			max=4;
		}
		maxNeeded=max;
		
		isPrime=new boolean[max+1];
		currentPrime=0;
		
		//Assume all integers are prime
		//Run through each except the first 2 booleans and set them to true
		for(int i=2; i<=max; i++)
		{
			isPrime[i]=true;
		}
	}
	
	//runs through each number past the last prime returned from next(), if any is found to be prime it immediately returns true
	@Override
	public boolean hasNext() {
		boolean currentCheck=false;

		for(int i=currentPrime+1; i<isPrime.length && !currentCheck; i++)
		{
			currentCheck=isPrime[i];
		}

		return currentCheck;
	}

	@Override
	public Integer next() {
		for(int i=currentPrime+1; i<isPrime.length; i++)
		{
			if(isPrime[i])
			{
				for(int j=i; i*j<=maxNeeded; j++)
				{
					isPrime[i*j]=false;
				}
				
				currentPrime=i;
				return currentPrime;
			}
		}
		return currentPrime;
	}
	
}

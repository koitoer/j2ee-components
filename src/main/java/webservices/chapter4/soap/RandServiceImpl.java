package webservices.chapter4.soap;

import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebService;

//Service Implementation Bean
@WebService
public class RandServiceImpl implements RandService {

	private static final int maxRands = 16;

	@Override
	@WebMethod
	public int next1(){
		return new Random().nextInt();
	}

	@Override
	@WebMethod
	public int[] nextN(final int n){
		final int k = (n > maxRands) ? maxRands : Math.abs(n);
		int[] rands = new int[k];

		Random r = new Random();
		for (int i = 0; i < k; i++)
			rands[i] = r.nextInt();
		return rands;
	}
}

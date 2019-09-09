package ws.soap.service.random;

import java.util.Random;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(
        name = "RandomServicePortType",
        portName = "RandomServicePort",
        serviceName = "RandomService",
        endpointInterface = "com.koitoer.jax.ws.soap.service.random.RandServiceImpl")
@HandlerChain(file = "randService-handler-chain.xml")
public class RandServiceImpl implements RandService {

	@Override
	@WebMethod
	public int next1(){
		System.out.println("Callin netxt1");
		return new Random().nextInt();
	}

	@Override
	@WebMethod
	public int[] nextN(final int n){
		final int k = (n > 10) ? 10 : Math.abs(n);
		int[] rands = new int[k];
		Random r = new Random();
		for (int i = 0; i < k; i++)
			rands[i] = r.nextInt();
		return rands;
	}

}

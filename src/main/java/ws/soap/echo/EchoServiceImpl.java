package ws.soap.echo;

import java.util.Date;

import javax.jws.WebService;

@WebService(endpointInterface = "com.koitoer.jax.ws.soap.echo.EchoService")
public class EchoServiceImpl implements EchoService {

	@Override
	public String echo(String name){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			return null;
		}
		return name + " : " + new Date();
	}

}

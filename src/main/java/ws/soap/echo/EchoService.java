package ws.soap.echo;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface EchoService {

	@WebMethod
	public abstract String echo(String name);

}

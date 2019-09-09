package ws.soap.service.random;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface RandService {

	@WebMethod
	public abstract int next1();

	@WebMethod
	public abstract int[] nextN(int n);

}
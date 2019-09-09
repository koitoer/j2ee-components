package webservices.chapter4.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

//Service Endpoint Interface SEI
@WebService
public interface RandService {

	@WebMethod
	public int next1();

	@WebMethod
	public int[] nextN(final int n);
}

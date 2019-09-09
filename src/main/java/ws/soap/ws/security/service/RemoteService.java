package ws.soap.ws.security.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class RemoteService {

	@WebMethod
	public String invoke(String msg){
		System.out.println("Invoke method in Remote Service has been called ");
		return "Invoking: " + msg;
	}
}

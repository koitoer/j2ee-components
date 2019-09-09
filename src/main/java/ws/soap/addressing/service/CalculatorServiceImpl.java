package ws.soap.addressing.service;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.Addressing;

@WebService
@Addressing(enabled = true, required = true)
public class CalculatorServiceImpl implements CalculatorService {

	@Resource
	private WebServiceContext context;

	@Override
	@WebMethod(operationName = "add")
	@Action(input = "http://addnumbers.org/input",
	        output = "http://addnumbers.org/output",
	        fault = {
	                @FaultAction(
	                        className = AddNumbersException.class,
	                        value = "http://example.com/fault3")
	        })
	public int addition(@WebParam(name = "param1") int i, @WebParam(name = "param2") int o)
	        throws AddNumbersException{
		System.out.println("Endpoint Reference : " + context.getEndpointReference());
		System.out.println("User principal : " + context.getUserPrincipal());
		System.out.println("Message Context : "
		        + context.getMessageContext().get(MessageContext.WSDL_SERVICE));

		return i + o;
	}

}

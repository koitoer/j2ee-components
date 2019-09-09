package ws.soap.ws.security.service.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.soap.SOAPBinding;

public class RemoteServiceClient {

	public static void main(String args[]) throws SOAPException{
		String targetNamespace = "http://service.security.ws.soap.ws.jax.koitoer.com/";
		QName serviceName = new QName(targetNamespace, "RemoteServiceService");
		QName portName = new QName(targetNamespace, "RemoteServicePort");
		String endpointAddress = "http://localhost:9998/serviceRPC";

		Service service = Service.create(serviceName);
		service.setHandlerResolver(new HandlerResolver(){

			@Override
			public List<Handler> getHandlerChain(PortInfo portInfo){
				List<Handler> list = new ArrayList<Handler>();
				list.add(new RemoteServiceClientHandler());
				return list;
			}
		});

		service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
		// Create a dispatch instance
		Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class,
		        Service.Mode.MESSAGE);

		SOAPMessage message = createMessage(dispatch, targetNamespace);
		// Invoke the endpoint synchronously
		SOAPMessage reply = null;

		try {
			// Invoke Endpoint Operation and read response
			reply = dispatch.invoke(message);
		} catch (javax.xml.ws.soap.SOAPFaultException faultException) {
			System.out.println("\n" + "FATAL ERROR : " + faultException.getMessage());
			return;
		} catch (WebServiceException wse) {
			wse.printStackTrace();
		}

		SOAPBody body = reply.getSOAPBody();

		QName responseName = new QName(targetNamespace, "invokeResponse");
		SOAPBodyElement bodyElement = (SOAPBodyElement) body.getChildElements(responseName)
		        .next();
		String message2 = bodyElement.getTextContent();
		System.out.println(message2);

	}

	private static SOAPMessage createMessage(Dispatch<SOAPMessage> dispatch, String targetNamespace)
	        throws SOAPException{

		// Use Dispatch as BindingProvider
		BindingProvider bp = dispatch;

		// Obtain a preconfigured SAAJ MessageFactory
		MessageFactory mf = MessageFactory.newInstance();

		SOAPMessage message = mf.createMessage();
		SOAPPart mySPart = message.getSOAPPart();
		SOAPEnvelope myEnvp = mySPart.getEnvelope();
		SOAPBody body = myEnvp.getBody();

		Name bodyName = myEnvp.createName("invoke", "m", targetNamespace);
		SOAPBodyElement gltp = body.addBodyElement(bodyName);

		Name req = myEnvp.createName("arg0");
		gltp.addChildElement(req).addTextNode("call()");

		return message;

	}
}

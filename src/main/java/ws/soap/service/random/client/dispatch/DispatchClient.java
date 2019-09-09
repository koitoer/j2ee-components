package ws.soap.service.random.client.dispatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.koitoer.jax.ws.soap.service.random.handler.MacAddressInjectHandler;

public class DispatchClient {

	public static void main(String args[]) throws SOAPException{

		String targetNamespace = "http://random.service.soap.ws.jax.koitoer.com/";
		QName serviceName = new QName(targetNamespace, "RandomService");
		QName portName = new QName(targetNamespace, "RandomServicePort");
		String endpointAddress = "http://localhost:8889/rs";

		// Create a dynamic Service instance
		Service service = Service.create(serviceName);
		service.setHandlerResolver(new HandlerResolver(){

			@Override
			public List<Handler> getHandlerChain(PortInfo portInfo){
				List<Handler> list = new ArrayList<Handler>();
				list.add(new MacAddressInjectHandler());
				return list;
			}
		});

		service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

		// Create a dispatch instance
		Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class,
		        Service.Mode.MESSAGE);

		// Use Dispatch as BindingProvider
		BindingProvider bp = dispatch;

		// Optionally Configure RequestContext to send SOAPAction HTTP Header
		Map<String, Object> rc = bp.getRequestContext();
		rc.put(BindingProvider.SOAPACTION_USE_PROPERTY, Boolean.TRUE);
		rc.put(BindingProvider.SOAPACTION_URI_PROPERTY, "");

		// Obtain a preconfigured SAAJ MessageFactory
		MessageFactory mf = MessageFactory.newInstance();

		SOAPMessage message = mf.createMessage();
		SOAPPart mySPart = message.getSOAPPart();
		SOAPEnvelope myEnvp = mySPart.getEnvelope();
		SOAPBody body = myEnvp.getBody();

		Name bodyName = myEnvp.createName("next1", "m", targetNamespace);
		SOAPBodyElement gltp = body.addBodyElement(bodyName);

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

		body = reply.getSOAPBody();

		Iterator<?> it = body.getChildElements(new QName(targetNamespace, "next1Response",
		        "ns2"));
		Node node = (Node) it.next();
		NodeList nodelist = node.getChildNodes();
		Node nodeReturn = nodelist.item(0);
		System.out.println(nodeReturn.getTextContent());

		QName responseName = new QName(targetNamespace, "next1Response");
		SOAPBodyElement bodyElement = (SOAPBodyElement) body.getChildElements(responseName)
		        .next();
		String message2 = bodyElement.getTextContent();
		System.out.println(message2);

	}
}

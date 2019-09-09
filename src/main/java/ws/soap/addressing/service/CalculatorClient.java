package ws.soap.addressing.service;

import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPBinding;

public class CalculatorClient {

	public static void main(String[] args) throws Exception{
		String targetNamespace = "http://service.addressing.soap.ws.jax.koitoer.com/";
		QName serviceName = new QName(targetNamespace, "CalculatorServiceImplService");
		QName portName = new QName(targetNamespace, "CalculatorServiceImplPort");
		String endpointAddress = "http://localhost:9998/calculator";

		// Create a dynamic Service instance
		// Enable addressing is not being support for metro implementation
		// Service service = Service.create(serviceName, new
		// AddressingFeature(true, true));
		Service service = Service.create(serviceName);
		service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

		// Create a dispatch instance
		Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class,
		        Service.Mode.MESSAGE);

		// Use Dispatch as BindingProvider
		BindingProvider bp = dispatch;

		// Optionally Configure RequestContext to send SOAPAction HTTP Header
		Map<String, Object> rc = bp.getRequestContext();
		rc.put(BindingProvider.SOAPACTION_USE_PROPERTY, Boolean.TRUE);
		rc.put(BindingProvider.SOAPACTION_URI_PROPERTY, "http://addnumbers.org/input");

		// Obtain a preconfigured SAAJ MessageFactory
		MessageFactory mf = MessageFactory.newInstance();

		SOAPMessage message = mf.createMessage();
		SOAPPart mySPart = message.getSOAPPart();
		SOAPEnvelope myEnvp = mySPart.getEnvelope();
		SOAPBody body = myEnvp.getBody();

		Name bodyName = myEnvp.createName("add", "m", targetNamespace);
		SOAPBodyElement gltp = body.addBodyElement(bodyName);

		Name myContent = myEnvp.createName("param1");
		SOAPElement mySymbol = gltp.addChildElement(myContent);
		mySymbol.addTextNode("1");

		Name myContent2 = myEnvp.createName("param2");
		SOAPElement mySymbol2 = gltp.addChildElement(myContent2);
		mySymbol2.addTextNode("4");

		// Adding addressing information within the header.
		SOAPHeader header = message.getSOAPHeader();
		header.addNamespaceDeclaration("wsa", "http://www.w3.org/2005/08/addressing");
		Name addressing = myEnvp.createName("MessageID", "wsa",
		        "http://www.w3.org/2005/08/addressing");
		SOAPHeaderElement addr = header.addHeaderElement(addressing);
		addr.addTextNode("uuid:49ea7cc7-932d-4551-ae42-1273a864ecf3");
		addr.setMustUnderstand(true);

		Name qnameAction = myEnvp.createName("Action", "", "http://www.w3.org/2005/08/addressing");
		SOAPElement actionHeader = header.addChildElement(qnameAction);
		actionHeader.addTextNode("http://addnumbers.org/input");

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

		SOAPBody bodyResponse = reply.getSOAPBody();
		QName responseName = new QName(targetNamespace, "addResponse");
		SOAPBodyElement bodyElement = (SOAPBodyElement) bodyResponse.getChildElements(
		        responseName).next();
		System.out.println("Answer of the call is " + bodyElement.getTextContent());
	}
}

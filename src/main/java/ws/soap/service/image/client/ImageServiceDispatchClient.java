package ws.soap.service.image.client;

import java.util.Iterator;
import java.util.Map;

import javax.swing.*;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.koitoer.jax.ws.soap.utils.SOAPHelper;

public class ImageServiceDispatchClient {

	public static void main(String args[]) throws SOAPException,
	        DOMException{

		String targetNamespace = "http://image.service.soap.ws.jax.koitoer.com/";
		QName serviceName = new QName(targetNamespace, "ImageServiceImplService");
		QName portName = new QName(targetNamespace, "ImageServiceImplPort");
		String endpointAddress = "http://localhost:9998/imageService";

		// Create a dynamic Service instance
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
		rc.put(BindingProvider.SOAPACTION_URI_PROPERTY, "");

		// Obtain a preconfigured SAAJ MessageFactory
		MessageFactory mf = MessageFactory.newInstance();

		SOAPMessage message = mf.createMessage();
		SOAPPart mySPart = message.getSOAPPart();
		SOAPEnvelope myEnvp = mySPart.getEnvelope();
		SOAPBody body = myEnvp.getBody();

		Name bodyName = myEnvp.createName("getImage", "m", targetNamespace);
		SOAPBodyElement gltp = body.addBodyElement(bodyName);

		Name myContent = myEnvp.createName("arg0");
		SOAPElement mySymbol = gltp.addChildElement(myContent);
		mySymbol.addTextNode("rss.jpg");

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
		System.out.print(SOAPHelper.getSOAPMessageAsString(reply));
		Iterator<?> it = body
		        .getChildElements(new QName(targetNamespace, "getImageResponse", "ns2"));
		Node node = (Node) it.next();
		NodeList nodelist = node.getChildNodes();
		Node nodeReturn = nodelist.item(0);
		// System.out.println(nodeReturn.getTextContent());
		byte[] base64Image = Base64.decodeBase64(nodeReturn.getTextContent());

		JFrame frame = new JFrame("My Image");
		frame.setSize(300, 300);
		JLabel label = new JLabel(new ImageIcon(base64Image));
		frame.add(label);
		frame.setVisible(true);

		// Now turn to

	}
}

package ws.jaas;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

public class JAASExample {

	// To test this you need to turn on EchoPublisher.

	public static void main(String[] args) throws SOAPException, IOException{
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		// Retrieve different parts
		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();

		// Two ways to extract headers
		SOAPHeader soapHeader = soapEnvelope.getHeader();
		soapHeader = soapMessage.getSOAPHeader();

		// Two ways to extract body
		SOAPBody soapBody = soapEnvelope.getBody();
		soapBody = soapMessage.getSOAPBody();

		// To add some element
		SOAPFactory soapFactory = SOAPFactory.newInstance();

		Name bodyName = soapFactory.createName("PurchaseLineItems", "PO",
		        "http://sonata.fruitsgalore.com");
		SOAPBodyElement purchaseLineItems = soapBody.addBodyElement(bodyName);

		Name childName = soapFactory.createName("Order");
		SOAPElement order = purchaseLineItems.addChildElement(childName);

		childName = soapFactory.createName("Product");
		SOAPElement product = order.addChildElement(childName);
		product.addTextNode("Apple");

		childName = soapFactory.createName("Price");
		SOAPElement price = order.addChildElement(childName);
		price.addTextNode("1.56");

		childName = soapFactory.createName("Order");
		SOAPElement order2 = purchaseLineItems.addChildElement(childName);

		childName = soapFactory.createName("Product");
		SOAPElement product2 = order2.addChildElement(childName);
		product2.addTextNode("Peach");

		childName = soapFactory.createName("Price");
		SOAPElement price2 = order2.addChildElement(childName);
		price2.addTextNode("1.48");

		// Adding some header just for fun
		String nameSpace = "ns";
		String nameSpaceURI = "http://gizmos.com/NSURI";

		Name orderHeaderName = soapFactory.createName("orderDesk",
		        nameSpace, nameSpaceURI);
		SOAPHeaderElement orderHeader =
		        soapMessage.getSOAPHeader().addHeaderElement(orderHeaderName);
		orderHeader.setActor("http://gizmos.com/orders");

		Name shipping =
		        soapFactory.createName("shippingDesk", nameSpace, nameSpaceURI);
		SOAPHeaderElement shippingHeader =
		        soapEnvelope.getHeader().addHeaderElement(shipping);
		shippingHeader.setActor("http://gizmos.com/shipping");

		// SOAPBodyElement can hold SOAPElement

		soapMessage.writeTo(System.out);
		System.out.println("");

		// If we want to send
		SOAPConnectionFactory soapConnectionFactory =
		        SOAPConnectionFactory.newInstance();
		SOAPConnection connection =
		        soapConnectionFactory.createConnection();
		URL endpoint = new URL("http://localhost:9998/echo");
		SOAPMessage response = connection.call(soapMessage, endpoint);

		// This is not a valid message so we would receive SOAPFAULT
		if (response.getSOAPBody().hasFault()) {
			SOAPFault soapFault = response.getSOAPBody().getFault();
			System.out.println(soapFault.getFaultCode());
			System.out.println(soapFault.getFaultString());

			Detail newDetail = soapFault.getDetail();
			if (newDetail != null) {
				Iterator entries = newDetail.getDetailEntries();
				while (entries.hasNext()) {
					DetailEntry newEntry =
					        (DetailEntry) entries.next();
					String value = newEntry.getValue();
					System.out.println("  Detail entry = " + value);
				}
			}
		}

		// Sending the real code and good code
		soapMessage = messageFactory.createMessage();
		soapBody = soapMessage.getSOAPBody();

		bodyName = soapFactory.createName("echo", "s",
		        "http://echo.soap.ws.jax.koitoer.com/");
		SOAPBodyElement echoLine = soapBody.addBodyElement(bodyName);
		childName = soapFactory.createName("arg0");
		SOAPElement echoMessage = echoLine.addChildElement(childName);
		echoMessage.addTextNode("hi echo");

		// If we want to send
		soapConnectionFactory =
		        SOAPConnectionFactory.newInstance();
		connection =
		        soapConnectionFactory.createConnection();
		response = connection.call(soapMessage, endpoint);

		// To read the response using SAAJ.
		SOAPBody soapBody1 = response.getSOAPBody();
		bodyName = soapFactory.createName("echoResponse", "ns2",
		        "http://echo.soap.ws.jax.koitoer.com/");
		Iterator iterator = soapBody1.getChildElements(bodyName);
		SOAPBodyElement bodyElement = (SOAPBodyElement) iterator.next();
		System.out.println("Message from the server is " + bodyElement.getTextContent());
	}

}

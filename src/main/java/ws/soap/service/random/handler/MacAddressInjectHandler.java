package ws.soap.service.random.handler;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import com.koitoer.jax.ws.soap.utils.SOAPHelper;

public class MacAddressInjectHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context){

		if ((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
			try {
				SOAPMessage soapMsg = context.getMessage();
				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();

				// if no header, add one
				if (soapHeader == null) {
					soapHeader = soapEnv.addHeader();
				}

				// get mac address
				String mac = "90-4C-E5-44-B9-8F";

				// add a soap header, name as "mac address"
				QName qname = new QName("http://random.service.soap.ws.jax.koitoer.com/",
				        "macAddress");
				SOAPHeaderElement soapHeaderElement = soapHeader.addHeaderElement(qname);

				soapHeaderElement.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
				soapHeaderElement.addTextNode(mac);
				soapMsg.saveChanges();

				System.out.println("OUTGOING REQUEST");
				System.out.println(SOAPHelper.getSOAPMessageAsString(soapMsg));
				System.out.println("END OUTGOING REQUEST");

			} catch (SOAPException e) {
				System.err.println(e.getMessage());
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close(MessageContext context){
		// TODO Auto-generated method stub

	}

	@Override
	public Set<QName> getHeaders(){
		// TODO Auto-generated method stub
		return null;
	}

}

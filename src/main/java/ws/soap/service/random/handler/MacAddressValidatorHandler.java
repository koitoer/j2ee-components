package ws.soap.service.random.handler;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.w3c.dom.Node;

import com.koitoer.jax.ws.soap.utils.SOAPHelper;

public class MacAddressValidatorHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context){

		Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		try {
			if (!isRequest) {
				SOAPMessage soapMsg = context.getMessage();
				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();

				// if no header, add one
				if (soapHeader == null) {
					soapHeader = soapEnv.addHeader();
					generateSOAPErrMessage(soapMsg, "No SOAP header.");
				}

				// Get client mac address from SOAP header
				Iterator it = soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);

				if (it == null || !it.hasNext()) {
					generateSOAPErrMessage(soapMsg, "No header block for next actor.");
				}

				Node macNode = (Node) it.next();
				String macValue = (macNode == null) ? null : macNode.getTextContent();

				if (macValue == null) {
					generateSOAPErrMessage(soapMsg, "No mac address in header block.");
				}

				if (!macValue.equals("90-4C-E5-44-B9-8F")) {
					generateSOAPErrMessage(soapMsg, "Invalid mac address, access is denied.");
				}

				System.out.println("INCOMING REQUEST");
				System.out.println(SOAPHelper.getSOAPMessageAsString(soapMsg));
				System.out.println("END INCOMING REQUEST");
			}
		} catch (SOAPException e) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	private void generateSOAPErrMessage(SOAPMessage msg, String reason) throws SOAPException{
		SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
		SOAPFault soapFault = soapBody.addFault();
		soapFault.setFaultString(reason);
		throw new SOAPFaultException(soapFault);
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

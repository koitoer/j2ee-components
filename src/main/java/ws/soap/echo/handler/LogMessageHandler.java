package ws.soap.echo.handler;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class LogMessageHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context){
		try {
			if ((Boolean) context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
				SOAPMessage message = context.getMessage();
				SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
				SOAPHeader header = envelope.getHeader();
				if (null == header)
					header = envelope.addHeader();

				QName credential = new QName("http://soap.header.test.com", "Credential");
				SOAPHeaderElement headerElement = header.addHeaderElement(credential);
				QName username = new QName("username");
				headerElement.addAttribute(username, "ashok");
				QName password = new QName("password");
				headerElement.addAttribute(password, "123");

				message.writeTo(System.out);
				return true;
			}

		} catch (Exception e) {
			System.err.println("An error in handler occurs.");
		}
		return false;
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

package ws.soap.ws.security.service;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import com.sun.xml.wss.ProcessingContext;
import com.sun.xml.wss.XWSSProcessor;
import com.sun.xml.wss.XWSSProcessorFactory;

public class RemoteServiceHandler implements SOAPHandler<SOAPMessageContext> {

	// An XWSSProcessor can add/verify Security in a SOAPMessage as defined by
	// the OASIS WSS 1.0 specification.
	private XWSSProcessor xwssServer = null;
	private final boolean trace;

	public RemoteServiceHandler() {
		XWSSProcessorFactory fact = null;
		try {
			fact = XWSSProcessorFactory.newInstance();
			ByteArrayInputStream config = getConfig();
			xwssServer = fact.createProcessorForSecurityConfiguration(config, new Verifier());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		trace = true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext msgCtx){
		Boolean outbound = (Boolean) msgCtx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		SOAPMessage msg = msgCtx.getMessage();
		if (!outbound.booleanValue()) {
			try {
				ProcessingContext pCtx = xwssServer.createProcessingContext(msg);
				pCtx.setSOAPMessage(msg);
				SOAPMessage verifiedMsg = xwssServer.verifyInboundMessage(pCtx);
				msgCtx.setMessage(verifiedMsg);
				if (trace)
					dump("Incoming message:", verifiedMsg);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return true;
	}

	@Override
	public Set<QName> getHeaders(){
		String uri = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
		QName securityHdr = new QName(uri, "Security", "wsse");
		HashSet<QName> headers = new HashSet<QName>();
		headers.add(securityHdr);
		return headers;
	}

	@Override
	public void close(MessageContext arg0){}

	@Override
	public boolean handleFault(SOAPMessageContext arg0){
		return true;
	}

	// InputStream for the SecurityConfiguration XML to be used by the
	// XWSSProcessor
	private ByteArrayInputStream getConfig(){
		String config =
		        "<xwss:SecurityConfiguration xmlns:xwss=\"http://java.sun.com/xml/ns/xwss/config\" dumpMessages=\"true\">"
		                + "<xwss:RequireUsernameToken passwordDigestRequired=\"false\"/> " +
		                "</xwss:SecurityConfiguration>";
		return new ByteArrayInputStream(config.getBytes());
	}

	private void dump(String msg, SOAPMessage soapMsg){
		try {
			System.out.println(msg);
			soapMsg.writeTo(System.out);
			System.out.println();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

package ws.soap.ws.security.service.client;

import java.io.File;
import java.io.FileInputStream;
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

public class RemoteServiceClientHandler implements SOAPHandler<SOAPMessageContext> {

	private XWSSProcessor xwssClient;
	private final boolean trace;

	public RemoteServiceClientHandler() {
		XWSSProcessorFactory fact = null;
		try {
			fact = XWSSProcessorFactory.newInstance();
			FileInputStream config = new FileInputStream(new File(
			        "src/main/resources/wss/client.xml"));
			xwssClient = fact.createProcessorForSecurityConfiguration(config, new Prompter());
			config.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		trace = true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext msgCtx){
		Boolean outbound = (Boolean) msgCtx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		SOAPMessage msg = msgCtx.getMessage();
		if (outbound.booleanValue()) {
			ProcessingContext pCtx = null;
			try {
				pCtx = xwssClient.createProcessingContext(msg);
				pCtx.setSOAPMessage(msg);
				SOAPMessage secureMsg = xwssClient.secureOutboundMessage(pCtx);
				msgCtx.setMessage(secureMsg);
				if (trace)
					dump("Outgoing message:", secureMsg);
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
		return false;
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

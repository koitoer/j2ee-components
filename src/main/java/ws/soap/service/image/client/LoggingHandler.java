package ws.soap.service.image.client;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import com.koitoer.jax.ws.soap.utils.SOAPHelper;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context){
		String a = SOAPHelper.getSOAPMessageAsString(context.getMessage());
		System.out.println(a);
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

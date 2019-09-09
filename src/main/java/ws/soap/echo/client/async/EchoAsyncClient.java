package ws.soap.echo.client.async;

import java.util.concurrent.ExecutionException;

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
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Response;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPBinding;

public class EchoAsyncClient {

	public static void main(String[] args) throws SOAPException, InterruptedException,
	        ExecutionException{

		final String targetNamespace = "http://echo.soap.ws.jax.koitoer.com/";
		QName serviceName = new QName(targetNamespace, "EchoServiceImplService");
		QName portName = new QName(targetNamespace, "EchoServiceImplPort");
		String endpointAddress = "http://localhost:9998/echo";

		// Create a dynamic Service instance
		Service service = Service.create(serviceName);
		service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

		// Create a dispatch instance
		Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class,
		        Service.Mode.MESSAGE);

		// Use Dispatch as BindingProvider
		BindingProvider bp = dispatch;

		// Obtain a preconfigured SAAJ MessageFactory
		MessageFactory mf = MessageFactory.newInstance();

		SOAPMessage message = mf.createMessage();
		SOAPPart mySPart = message.getSOAPPart();
		SOAPEnvelope myEnvp = mySPart.getEnvelope();
		SOAPBody body = myEnvp.getBody();

		Name bodyName = myEnvp.createName("echo", "m", targetNamespace);
		SOAPBodyElement gltp = body.addBodyElement(bodyName);
		QName qname = new QName("arg0");
		SOAPElement element = gltp.addChildElement(qname);
		element.addTextNode("Koitoer...");

		// Before call the service create the handler
		AsyncHandler<SOAPMessage> handler = new AsyncHandler<SOAPMessage>(){

			@Override
			public void handleResponse(Response<SOAPMessage> res){
				SOAPMessage soapMessage = null;
				SOAPBody bodyResponse = null;
				try {
					soapMessage = res.get();
					bodyResponse = soapMessage.getSOAPBody();
				} catch (Exception e) {
					System.err.println("Something goes wrong in the handler");
				}
				QName responseName = new QName(targetNamespace, "echoResponse");
				SOAPBodyElement bodyElement = (SOAPBodyElement) bodyResponse.getChildElements(
				        responseName).next();
				System.out.println(bodyElement.getTextContent() + " > "
				        + Thread.currentThread().getId());
			}
		};

		try {
			// Invoke Endpoint Operation and read response
			System.out.println("Before");
			dispatch.invokeAsync(message, handler);
			dispatch.invokeAsync(message, handler);
			dispatch.invokeAsync(message, handler);
			System.out.println("After");
		} catch (javax.xml.ws.soap.SOAPFaultException faultException) {
			System.out.println("\n" + "FATAL ERROR : " + faultException.getMessage());
			return;
		} catch (WebServiceException wse) {
			wse.printStackTrace();
		}

		System.out.println("Main thread is waiting " + Thread.currentThread());
		Thread.sleep(10000);
		System.out.println("Main thread is ending " + Thread.currentThread());
	}

}

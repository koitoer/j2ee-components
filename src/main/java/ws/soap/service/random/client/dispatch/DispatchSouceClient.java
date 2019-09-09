package ws.soap.service.random.client.dispatch;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.koitoer.jax.ws.soap.service.random.client.Next1;
import com.koitoer.jax.ws.soap.service.random.client.Next1Response;

public class DispatchSouceClient {

	public static void main(String[] args) throws Exception{
		String targetNamespace = "http://random.service.soap.ws.jax.koitoer.com/";
		QName serviceName = new QName(targetNamespace, "RandomService");
		QName portName = new QName(targetNamespace, "RandomServicePort");
		String endpointAddress = "http://localhost:8889/rs";

		// Create a dynamic Service instance
		Service service = Service.create(serviceName);
		service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

		// Create a dispatch instance
		Dispatch<Source> dispatch = service.createDispatch(portName, Source.class,
		        Service.Mode.MESSAGE);

		StreamSource msg = new StreamSource(new File("src/main/resources/dispatchSource.xml"));
		Source response = dispatch.invoke(msg);
		SAXSource sax = (SAXSource) response;

		DOMResult result = new DOMResult();
		TransformerFactory.newInstance().newTransformer().transform(sax, result);
		Document doc = (Document) result.getNode();
		NodeList nodelist = doc.getChildNodes();
		Node nodeReturn = nodelist.item(0);

		System.out.println(nodeReturn.getTextContent());

		// Using JAXB
		JAXBContext jc = JAXBContext.newInstance("com.koitoer.jax.ws.soap.service.random.client");
		Next1 request = new Next1();
		Dispatch<Object> disp = service.createDispatch(portName, jc, Service.Mode.PAYLOAD);
		@SuppressWarnings("unchecked")
		javax.xml.bind.JAXBElement<Next1Response> responseObject = (javax.xml.bind.JAXBElement<Next1Response>) disp
		        .invoke(request);
		System.out.println(responseObject.getValue().getReturn());
	}
}

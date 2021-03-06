package webservices.chapter4.client.wsimport.async;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.4-b01 Generated
 * source version: 2.2
 * 
 */
@WebServiceClient(
        name = "RandServiceImplService",
        targetNamespace = "http://soap.chapter4.webservices.koitoer.com/",
        wsdlLocation = "http://localhost:8080/web-services/soap-webservice/randService/?wsdl")
public class RandServiceImplService
        extends Service
{

	private final static URL RANDSERVICEIMPLSERVICE_WSDL_LOCATION;
	private final static WebServiceException RANDSERVICEIMPLSERVICE_EXCEPTION;
	private final static QName RANDSERVICEIMPLSERVICE_QNAME = new QName(
	        "http://soap.chapter4.webservices.koitoer.com/",
	        "RandServiceImplService");

	static {
		URL url = null;
		WebServiceException e = null;
		try {
			url = new URL(
			        "http://localhost:8080/web-services/soap-webservice/randService/?wsdl");
		} catch (MalformedURLException ex) {
			e = new WebServiceException(ex);
		}
		RANDSERVICEIMPLSERVICE_WSDL_LOCATION = url;
		RANDSERVICEIMPLSERVICE_EXCEPTION = e;
	}

	public RandServiceImplService() {
		super(__getWsdlLocation(), RANDSERVICEIMPLSERVICE_QNAME);
	}

	public RandServiceImplService(WebServiceFeature... features) {
		super(__getWsdlLocation(), RANDSERVICEIMPLSERVICE_QNAME);
	}

	public RandServiceImplService(URL wsdlLocation) {
		super(wsdlLocation, RANDSERVICEIMPLSERVICE_QNAME);
	}

	public RandServiceImplService(URL wsdlLocation,
	        WebServiceFeature... features) {
		super(wsdlLocation, RANDSERVICEIMPLSERVICE_QNAME);
	}

	public RandServiceImplService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public RandServiceImplService(URL wsdlLocation, QName serviceName,
	        WebServiceFeature... features) {
		super(wsdlLocation, serviceName);
	}

	/**
	 * 
	 * @return returns RandServiceImpl
	 */
	@WebEndpoint(name = "RandServiceImplPort")
	public RandServiceImpl getRandServiceImplPort(){
		return super.getPort(new QName(
		        "http://soap.chapter4.webservices.koitoer.com/",
		        "RandServiceImplPort"), RandServiceImpl.class);
	}

	/**
	 * 
	 * @param features
	 *            A list of {@link WebServiceFeature} to configure
	 *            on the proxy. Supported features not in the
	 *            <code>features</code> parameter will have their default
	 *            values.
	 * @return returns RandServiceImpl
	 */
	@WebEndpoint(name = "RandServiceImplPort")
	public RandServiceImpl getRandServiceImplPort(WebServiceFeature... features){
		return super.getPort(new QName(
		        "http://soap.chapter4.webservices.koitoer.com/",
		        "RandServiceImplPort"), RandServiceImpl.class, features);
	}

	private static URL __getWsdlLocation(){
		if (RANDSERVICEIMPLSERVICE_EXCEPTION != null) {
			throw RANDSERVICEIMPLSERVICE_EXCEPTION;
		}
		return RANDSERVICEIMPLSERVICE_WSDL_LOCATION;
	}

}

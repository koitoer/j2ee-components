package ws.soap.service.random.client;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.4-b01 Generated
 * source version: 2.2
 * 
 */
@WebService(
        name = "RandomServicePortType",
        targetNamespace = "http://random.service.soap.ws.jax.koitoer.com/")
@XmlSeeAlso({
        ObjectFactory.class
})
public interface RandomServicePortType {

	/**
	 * 
	 * @return returns int
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(
	        localName = "next1",
	        targetNamespace = "http://random.service.soap.ws.jax.koitoer.com/",
	        className = "com.koitoer.jax.ws.soap.service.random.client.Next1")
	@ResponseWrapper(
	        localName = "next1Response",
	        targetNamespace = "http://random.service.soap.ws.jax.koitoer.com/",
	        className = "com.koitoer.jax.ws.soap.service.random.client.Next1Response")
	@Action(
	        input = "http://random.service.soap.ws.jax.koitoer.com/RandomServicePortType/next1Request",
	        output = "http://random.service.soap.ws.jax.koitoer.com/RandomServicePortType/next1Response")
	public int next1();

	/**
	 * 
	 * @param arg0
	 * @return returns java.util.List<java.lang.Integer>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(
	        localName = "nextN",
	        targetNamespace = "http://random.service.soap.ws.jax.koitoer.com/",
	        className = "com.koitoer.jax.ws.soap.service.random.client.NextN")
	@ResponseWrapper(
	        localName = "nextNResponse",
	        targetNamespace = "http://service.soap.ws.jax.koitoer.com/",
	        className = "com.koitoer.jax.ws.soap.service.random.client.NextNResponse")
	@Action(
	        input = "http://random.service.soap.ws.jax.koitoer.com/RandomServicePortType/nextNRequest",
	        output = "http://random.service.soap.ws.jax.koitoer.com/RandomServicePortType/nextNResponse")
	public List<Integer> nextN(
        @WebParam(name = "arg0", targetNamespace = "") int arg0);

}
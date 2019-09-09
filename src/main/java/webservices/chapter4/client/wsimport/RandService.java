
package webservices.chapter4.client.wsimport;

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
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "RandService", targetNamespace = "http://soap.chapter4.webservices.koitoer.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface RandService {


    /**
     * 
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "next1", targetNamespace = "http://soap.chapter4.webservices.koitoer.com/", className = "com.koitoer.webservices.chapter4.client.wsimport.Next1")
    @ResponseWrapper(localName = "next1Response", targetNamespace = "http://soap.chapter4.webservices.koitoer.com/", className = "com.koitoer.webservices.chapter4.client.wsimport.Next1Response")
    @Action(input = "http://soap.chapter4.webservices.koitoer.com/RandService/next1Request", output = "http://soap.chapter4.webservices.koitoer.com/RandService/next1Response")
    public int next1();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.Integer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "nextN", targetNamespace = "http://soap.chapter4.webservices.koitoer.com/", className = "com.koitoer.webservices.chapter4.client.wsimport.NextN")
    @ResponseWrapper(localName = "nextNResponse", targetNamespace = "http://soap.chapter4.webservices.koitoer.com/", className = "com.koitoer.webservices.chapter4.client.wsimport.NextNResponse")
    @Action(input = "http://soap.chapter4.webservices.koitoer.com/RandService/nextNRequest", output = "http://soap.chapter4.webservices.koitoer.com/RandService/nextNResponse")
    public List<Integer> nextN(
        @WebParam(name = "arg0", targetNamespace = "")
            int arg0);

}

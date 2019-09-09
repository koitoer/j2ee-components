
package ws.soap.echo.client.genAsync;

import java.util.concurrent.Future;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.Response;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EchoService", targetNamespace = "http://echo.soap.ws.jax.koitoer.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EchoService {


    /**
     * 
     * @param arg0
     * @return
     *     returns javax.xml.ws.Response<com.koitoer.jax.ws.soap.echo.client.genAsync.EchoResponse>
     */
    @WebMethod(operationName = "echo")
    @RequestWrapper(localName = "echo", targetNamespace = "http://echo.soap.ws.jax.koitoer.com/", className = "com.koitoer.jax.ws.soap.echo.client.genAsync.Echo")
    @ResponseWrapper(localName = "echoResponse", targetNamespace = "http://echo.soap.ws.jax.koitoer.com/", className = "com.koitoer.jax.ws.soap.echo.client.genAsync.EchoResponse")
    public Response<EchoResponse> echoAsync(
        @WebParam(name = "arg0", targetNamespace = "")
            String arg0);

    /**
     *
     * @param arg0
     * @param asyncHandler
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "echo")
    @RequestWrapper(localName = "echo", targetNamespace = "http://echo.soap.ws.jax.koitoer.com/", className = "com.koitoer.jax.ws.soap.echo.client.genAsync.Echo")
    @ResponseWrapper(localName = "echoResponse", targetNamespace = "http://echo.soap.ws.jax.koitoer.com/", className = "com.koitoer.jax.ws.soap.echo.client.genAsync.EchoResponse")
    public Future<?> echoAsync(
        @WebParam(name = "arg0", targetNamespace = "")
            String arg0,
        @WebParam(name = "asyncHandler", targetNamespace = "")
            AsyncHandler<EchoResponse> asyncHandler);

    /**
     *
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "echo", targetNamespace = "http://echo.soap.ws.jax.koitoer.com/", className = "com.koitoer.jax.ws.soap.echo.client.genAsync.Echo")
    @ResponseWrapper(localName = "echoResponse", targetNamespace = "http://echo.soap.ws.jax.koitoer.com/", className = "com.koitoer.jax.ws.soap.echo.client.genAsync.EchoResponse")
    @Action(input = "http://echo.soap.ws.jax.koitoer.com/EchoService/echoRequest", output = "http://echo.soap.ws.jax.koitoer.com/EchoService/echoResponse")
    public String echo(
        @WebParam(name = "arg0", targetNamespace = "")
            String arg0);

}

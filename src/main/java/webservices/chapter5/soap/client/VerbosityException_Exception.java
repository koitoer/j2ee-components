
package webservices.chapter5.soap.client;

import javax.xml.ws.WebFault;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "VerbosityException", targetNamespace = "http://soaphandlers.chapter5.webservices.koitoer.com/")
public class VerbosityException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private VerbosityException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public VerbosityException_Exception(String message, VerbosityException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public VerbosityException_Exception(String message, VerbosityException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.koitoer.webservices.chapter5.soap.client.VerbosityException
     */
    public VerbosityException getFaultInfo() {
        return faultInfo;
    }

}

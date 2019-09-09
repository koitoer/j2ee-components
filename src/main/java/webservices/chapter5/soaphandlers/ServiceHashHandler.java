package webservices.chapter5.soaphandlers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ServiceHashHandler implements SOAPHandler<SOAPMessageContext> {

	private byte[] secretBytes;

	public ServiceHashHandler() {}

	@Override
	public void close(MessageContext mCtx){}

	@Override
	public Set<QName> getHeaders(){
		return null;
	}

	@Override
	public boolean handleFault(SOAPMessageContext mCtx){
		return true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext mCtx){
		Boolean outbound =
		        (Boolean) mCtx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!outbound) {
			try {
				System.out.println("Accesing handleMessage");
				SOAPMessage msg = mCtx.getMessage();
				SOAPHeader soapHeader = msg.getSOAPHeader();
				if (soapHeader == null)
					generateFault(msg, "No header!");

				Node node = soapHeader.getFirstChild(); // credentials
				NodeList nodeList = node.getChildNodes(); // Name, Timestamp,
				                                          // Signature
				if (nodeList.getLength() < 3)
					generateFault(msg, "Too few header blocks!");

				System.out.println("Before extract information");
				// Extract the required attributes.
				String name = "moe";// nodeList.item(0).getFirstChild().getNodeValue();
				String signature = "vkIQm8isdpomHeQ1aFqckzQawlZakj+hWOQaxsQcPgU=";// nodeList.item(1).getFirstChild().getNodeValue();
				String timestamp = "2014-03-26T01:12:47Z";// nodeList.item(2).getFirstChild().getNodeValue();
				System.out.println("After extract information");
				if (name == null || timestamp == null || signature == null)
					generateFault(msg, "Missing header key/value pairs!");

				// Generate comparison signature and compare against what's
				// sent.
				String secret = DataStore.get(name);
				if (secret == null)
					generateFault(msg, name + " not registered!");

				byte[] secretBytes = getBytes(secret);
				String localSignature = getSignature(name, timestamp,
				        secretBytes);

				if (!verify(signature, localSignature)) {
					System.out.println("Verify after");
					generateFault(msg, "HMAC signatures do not match.");
					System.out.println("Verify after after");
				}
			} catch (Exception e) {
				throw new RuntimeException("SOAPException thrown.", e);
			}
		}
		return true; // continue down the handler chain
	}

	private boolean verify(String sig1, String sig2){
		System.out.println(sig1.getBytes() + "::" + sig1.getBytes());
		return true;
		// Arrays.equals(sig1.getBytes(), sig2.getBytes());
	}

	private String getSignature(String name, String timestamp,
	        byte[] secretBytes){
		try {
			System.err.println("Name ==      " + name);
			System.err.println("Timestamp == " + timestamp);

			String toSign = name + timestamp;
			byte[] toSignBytes = getBytes(toSign);

			Mac signer = Mac.getInstance("HmacSHA256");
			SecretKeySpec keySpec = new SecretKeySpec(secretBytes, "HmacSHA256");

			signer.init(keySpec);
			signer.update(toSignBytes);
			byte[] signBytes = signer.doFinal();

			String signature = new String(Base64.encodeBase64(signBytes));
			System.out.println("Returning");
			return signature;
		} catch (Exception e) {
			throw new RuntimeException("NoSuchAlgorithmException thrown.", e);
		}
	}

	private String getTimestamp(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
		        "yyyy-MM-dd'T'HH:mm:ss'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return dateFormat.format(calendar.getTime());
	}

	private byte[] getBytes(String str){
		try {
			return str.getBytes("UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void generateFault(SOAPMessage msg, String reason){
		try {
			SOAPBody body = msg.getSOAPBody();
			SOAPFault fault = body.addFault();
			fault.setFaultString(reason);
			throw new SOAPFaultException(fault);
		} catch (SOAPException e) {}
	}
}

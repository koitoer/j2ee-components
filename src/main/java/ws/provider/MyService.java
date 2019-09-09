package ws.provider;

import java.io.StringReader;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Provider;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;

import com.sun.xml.ws.util.xml.StAXSource;

/**
 * @author mauricio.mena
 * @since 18/09/2014
 *
 */
@WebServiceProvider
@ServiceMode(Mode.PAYLOAD)
@SOAPBinding
public class MyService implements Provider<Source> {

	@Resource
	private WebServiceContext webServiceContext;

	/**
	 * 
	 */
	public MyService() {
		System.out.println("Raise service");
	}

	@Override
	public Source invoke(final Source request) {
		try {
			final StAXSource stAXSource = (StAXSource) request;
			final Transformer transformer = TransformerFactory.newInstance().newTransformer();
			final StringWriter stringWriter = new StringWriter();
			transformer.transform(stAXSource, new StreamResult(stringWriter));
			System.out.println(stringWriter.toString());
		}
		catch (final TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(webServiceContext.getMessageContext().toString());

		System.out.println(webServiceContext.getUserPrincipal());

		final String replyElement = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		final StreamSource replySource = new StreamSource(new StringReader(replyElement));
		return replySource;
	}

}

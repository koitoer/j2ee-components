package rs.ebook.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.koitoer.jax.rs.ebook.domain.Ebook;

@Path("/ebook")
public class EbookResourceImpl extends EbookResourceAbstract {

	@GET
	public String getMethod(){
		System.out.println("Execute getMethod.");
		return "Hellow Services";

	}

	@Override
	protected void writeEbook(OutputStream outputStream, Ebook ebook){
		PrintStream writer = new PrintStream(outputStream);
		writer.println("<ebook id=\"" + ebook.getId() + "\">");
		writer.println(" <title>" + ebook.getTitle() + "</title>");
		writer.println(" <author>" + ebook.getAuthor() + "</author>");
		writer.println(" <edition>" + ebook.getEdition() + "</edition>");
		writer.println(" <pageNumber>" + ebook.getNumberOfPages() + "</pageNumber>");
		writer.println("</ebook>");
	}

	@Override
	protected Ebook readEbook(InputStream is){
		Ebook ebook = new Ebook();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement();
			if (root.getAttribute("id") != null
			        && !root.getAttribute("id").trim().equals("")) {
				ebook.setId(Integer.parseInt(root.getAttribute("id")));
			}

			NodeList nodelist = root.getChildNodes();
			for (int i = 0; i < nodelist.getLength(); i++) {
				Element element = (Element) nodelist.item(i);
				if (element.getTagName().equals("title")) {
					ebook.setTitle(element.getTextContent());
				} else if (element.getTagName().equals("author")) {
					ebook.setAuthor(element.getTextContent());
				} else if (element.getTagName().equals("edition")) {
					ebook.setEdition(element.getTextContent());
				} else if (element.getTagName().equals("pageNumber")) {
					ebook.setNumberOfPages(Integer.parseInt(element.getTextContent()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ebook;
	}

}

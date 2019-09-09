package ws.soap.xop;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.soap.SOAPBinding;

import com.koitoer.jax.ws.soap.service.image.client.LoggingHandler;

public class ImageClient {

	public static void main(String[] args) throws Exception{

		URL url = new URL("http://localhost:9998/ws/image?wsdl");
		QName qname = new QName("http://xop.soap.ws.jax.koitoer.com/", "ImageServerImplService");

		Service service = Service.create(url, qname);
		ImageServer imageServer = service.getPort(ImageServer.class);

		BindingProvider bp = (BindingProvider) imageServer;
		SOAPBinding binding = (SOAPBinding) bp.getBinding();
		binding.setMTOMEnabled(true);
		List<Handler> handlers = new ArrayList<Handler>();
		handlers.add(new LoggingHandler());
		binding.setHandlerChain(handlers);

		/************ test download ***************/
		Image image = imageServer.downloadImage("rss.jpg");

		// display it in frame
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		JLabel label = new JLabel(new ImageIcon(image));
		frame.add(label);
		frame.setVisible(true);

		System.out.println("imageServer.downloadImage() : Download Successful!");

		// enable MTOM in client

		Image imgUpload = ImageIO.read(new File("src/main/resources/images/cat.jpg"));
		String status = imageServer.uploadImage(imgUpload);
		System.out.println("imageServer.uploadImage() : " + status);
	}
}
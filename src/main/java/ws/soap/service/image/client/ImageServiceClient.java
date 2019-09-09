package ws.soap.service.image.client;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.soap.MTOMFeature;
import javax.xml.ws.soap.SOAPBinding;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.koitoer.jax.ws.soap.service.image.ImageService;

public class ImageServiceClient {

	public static void main(String args[]) throws Exception{

		URL url = new URL("http://localhost:9999/imageService?wsdl");
		QName qname = new QName("http://image.service.soap.ws.jax.koitoer.com/",
		        "ImageServiceImplService");
		URL urlFile = new File("src/main/resources/wsdl/imageService.wsdl").toURI().toURL();
		Service service = Service.create(urlFile, qname);
		ImageService imageServer = service.getPort(ImageService.class);

		BindingProvider bp = (BindingProvider) imageServer;
		SOAPBinding binding = (SOAPBinding) bp.getBinding();
		List<Handler> handlers = new ArrayList<Handler>();
		handlers.add(new LoggingHandler());
		binding.setHandlerChain(handlers);

		binding.setMTOMEnabled(true);

		Image image = imageServer.getImage("rss.jpg");

		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		JLabel label = new JLabel(new ImageIcon(image));
		frame.add(label);
		frame.setVisible(true);

		// Now turn to upload file.
		Image imgUpload = ImageIO.read(new File("src/main/resources/images/cat.jpg"));
		String status = imageServer.uploadImage(imgUpload);
		System.out.println("imageServer.uploadImage() : " + status);

		// Now turn to upload big file
		MTOMFeature mtom = new MTOMFeature(true, 0);
		imageServer = service.getPort(ImageService.class, mtom);
		FileDataSource fileDataSource = new FileDataSource("src/main/resources/images/big.jpg");
		DataHandler data = new DataHandler(fileDataSource);

		bp = (BindingProvider) imageServer;
		binding = (SOAPBinding) bp.getBinding();
		binding.setHandlerChain(handlers);

		status = imageServer.uploadBigFile(data);
		System.out.println("imageServer.uploadBigFile() : " + status);

		// Now turn to binary data
		byte[] dataBinary = ByteStreams.toByteArray(new FileInputStream(new File(
		        "src/main/resources/images/big.jpg")));
		status = imageServer.uploadBinaryDate(dataBinary);
		System.out.println("imageServer.uploadBinaryDate() : " + status);

		// Turn to download binary data
		System.out.println("Binary");
		dataBinary = imageServer.downloadFile();
		Files.write(dataBinary, new File("src/main/resources/images/target.jpg"));
	}
}

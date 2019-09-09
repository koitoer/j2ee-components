package ws.soap.service.image;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.swing.*;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.BindingType;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;

import com.google.common.io.ByteStreams;

@MTOM(enabled = true)
@WebService(
        endpointInterface = "com.koitoer.jax.ws.soap.service.image.ImageService",
        wsdlLocation = "wsdl/imageService.wsdl")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP11HTTP_MTOM_BINDING)
public class ImageServiceImpl implements ImageService {

	@Override
	public Image getImage(String name){
		try {
			File image = new File("src/main/resources/images/" + name);
			System.out.println(image.getAbsolutePath());
			return ImageIO.read(image);
		} catch (IOException e) {
			System.err.println("Image can not be read " + e.getMessage());
			return null;
		}
	}

	@Override
	public String uploadImage(Image data){
		if (data != null) {
			JFrame frame = new JFrame("Server Image received");
			frame.setSize(300, 300);
			JLabel label = new JLabel(new ImageIcon(data));
			frame.add(label);
			frame.setVisible(true);
			return "Upload Successful";
		}
		throw new WebServiceException("Upload Failed!");
	}

	@Override
	public String uploadBigFile(@XmlMimeType("application/octet-stream") DataHandler data){
		try {
			InputStream is = data.getInputStream(); // DataHandler
			File file = new File("rockiett.jpg", "");
			OutputStream os = new FileOutputStream(file.getAbsolutePath());

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = is.read(buffer)) != -1)
			{
				os.write(buffer, 0, bytesRead);
			}
			os.flush();
			os.close();

			return "Upload Successful";
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		throw new WebServiceException("Upload Failed!");
	}

	@Override
	public String uploadBinaryDate(byte[] data){
		try {
			File file = new File("binary.jpg", "");
			OutputStream os = new FileOutputStream(file.getAbsolutePath());
			os.write(data);
			os.flush();
			os.close();
			return "Upload Successful";
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		throw new WebServiceException("Upload Failed!");
	}

	@Override
	public byte[] downloadFile(){
		File image = new File("src/main/resources/images/download.jpg");
		try {
			byte[] dataBinary = ByteStreams.toByteArray(new FileInputStream(image));
			return dataBinary;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

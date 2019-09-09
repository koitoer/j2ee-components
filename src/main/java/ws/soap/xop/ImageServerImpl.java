package ws.soap.xop;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;

@MTOM
@WebService(endpointInterface = "com.koitoer.jax.ws.soap.xop.ImageServer")
public class ImageServerImpl implements ImageServer {

	@Override
	public Image downloadImage(String name){
		try {
			File image = new File("src/main/resources/images/" + name);
			return ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public String uploadImage(Image data){
		if (data != null) {
			// store somewhere
			return "Upload Successful";
		}

		throw new WebServiceException("Upload Failed!");
	}

}

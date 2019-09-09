package webservices.chapter5.base64attach.server;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(wsdlLocation = "mtom.wsdl")
@BindingType(value = SOAPBinding.SOAP11HTTP_MTOM_BINDING)
public class SkiImageService {

	private final Map<String, String> photos;

	@WebMethod
	public Image getImage(String name){
		return createImage(name);
	}

	@WebMethod
	public List<Image> getImages(){
		return createImageList();
	}

	public SkiImageService() {
		photos = new HashMap<String, String>();
		photos.put("nordic", "src/main/resources/images/nordic.jpg");
		photos.put("alpine", "src/main/resources/images/alpine.jpg");
		photos.put("telemk", "src/main/resources/images/telemk.jpg");
	}

	private Image createImage(String name){
		String fileName = photos.get(name);
		byte[] bytes = getRawBytes(fileName);
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		Iterator iterators = ImageIO.getImageReadersByFormatName("jpeg");
		ImageReader iterator = (ImageReader) iterators.next();
		Image image = null;
		try {
			ImageInputStream iis = ImageIO.createImageInputStream(in);
			iterator.setInput(iis, true);
			image = iterator.read(0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return image;
	}

	private List<Image> createImageList(){
		List<Image> list = new ArrayList<Image>();
		for (String key : photos.keySet()) {
			Image image = createImage(key);
			if (image != null)
				list.add(image);
		}
		return list;
	}

	private byte[] getRawBytes(String fileName){
		if (fileName == null)
			fileName = "src/main/resources/images/nordic.jpg";

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			FileInputStream in = new FileInputStream(fileName);

			byte[] buffer = new byte[2048];
			int n = 0;
			while ((n = in.read(buffer)) != -1)
				out.write(buffer, 0, n); // append to array
			in.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return out.toByteArray();
	}
}
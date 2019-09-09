package ws.soap.service.image;

import java.awt.*;

import javax.activation.DataHandler;
import javax.jws.WebService;

@WebService
public interface ImageService {

	public abstract Image getImage(String name);

	public abstract String uploadImage(Image data);

	public abstract String uploadBigFile(DataHandler data);

	public abstract String uploadBinaryDate(byte[] data);

	public abstract byte[] downloadFile();

}
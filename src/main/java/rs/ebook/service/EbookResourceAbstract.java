package rs.ebook.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import com.koitoer.jax.rs.ebook.domain.Ebook;

public abstract class EbookResourceAbstract {

	private final Map<Integer, Ebook> ebookRepository = new ConcurrentHashMap<Integer, Ebook>();
	private final AtomicInteger idCounter = new AtomicInteger();

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public StreamingOutput getEbook(@PathParam("id") int id){
		System.out.println("Executing getEbook");
		final Ebook ebook = ebookRepository.get(id);
		if (ebook == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return new StreamingOutput(){

			@Override
			public void write(OutputStream outputStream)
			        throws IOException, WebApplicationException{
				writeEbook(outputStream, ebook);
			}
		};
	}

	@POST
	@Consumes("application/xml;charset=utf-8")
	public Response addEbook(InputStream is){
		System.out.println("Executing addEbook");
		Ebook ebook = readEbook(is);
		ebook.setId(idCounter.incrementAndGet());
		ebookRepository.put(ebook.getId(), ebook);
		System.out.println("Ebook added " + ebook.getId());
		return Response.created(URI.create(String.valueOf(ebook.getId()))).build();
	}

	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateEbook(@PathParam("id") int id, InputStream is){
		System.out.println("Executing updateEbook");
		Ebook eBook = readEbook(is);
		Ebook current = ebookRepository.get(id);
		if (current == null)
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		current.setAuthor(eBook.getAuthor());
		current.setEdition(eBook.getEdition());
		current.setNumberOfPages(eBook.getNumberOfPages());
		current.setTitle(eBook.getTitle());
	}

	// Below are aux method to read and write the XML

	protected abstract Ebook readEbook(InputStream is);

	protected abstract void writeEbook(OutputStream outputStream, Ebook ebook);

}

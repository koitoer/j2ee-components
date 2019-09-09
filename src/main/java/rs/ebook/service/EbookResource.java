package rs.ebook.service;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

public interface EbookResource {

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public StreamingOutput getEbook(@PathParam("id") int id);

	@POST
	@Consumes("application/xml")
	public Response addEbook(InputStream is);

	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateEbook(@PathParam("id") int id, InputStream is);
}

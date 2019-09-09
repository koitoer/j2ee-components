package rs.helloworld;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/form")
public class FormResource {

	private static final String FILE_PATH = "src/main/resources/MyFile.txt";
	private static final String IMAGE_PATH = "src/main/resources/adidas.png";

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addUser(
	        @FormParam("name") String name,
	        @FormParam("age") int age){

		return Response.status(200)
		        .entity("addUser is called, name : " + name + ", age : " + age)
		        .build();
	}

	@GET
	@Path("/get")
	public Response addUser(@HeaderParam("user-agent") String userAgent){

		return Response.status(200)
		        .entity("addUser is called, userAgent : " + userAgent)
		        .build();

	}

	@GET
	@Path("/getHeaders")
	public Response addUser(@Context HttpHeaders headers){

		String userAgent = headers.getRequestHeader("user-agent").get(0);

		for (String header : headers.getRequestHeaders().keySet()) {
			System.out.println(header);
		}

		return Response.status(200)
		        .entity("addUser is called, userAgent : " + userAgent)
		        .build();

	}

	@GET
	@Path("/getTxtFile")
	@Produces("text/plain")
	public Response getFile(){
		File file = new File(FILE_PATH);
		ResponseBuilder response = Response.ok(file);
		response.header("Content-Disposition",
		        "attachment; filename=\"file_from_server.log\"");
		return response.build();

	}

	@GET
	@Path("/getImageFile")
	@Produces("image/png")
	public Response getImageFile(){
		File file = new File(IMAGE_PATH);
		ResponseBuilder response = Response.ok(file);
		response.header("Content-Disposition",
		        "attachment; filename=image_from_server.png");

		return response.build();

	}

}

package rs.helloworld;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.koitoer.jax.rs.ebook.domain.Ebook;

@Path("/helloworld")
public class HelloWorldResource {

	@GET
	@Produces("text/plain")
	public String getClichedMessage(){
		return "Hello World";
	}

	@GET
	@Path("/request/{param}")
	public Response getMsg(@PathParam("param") String msg){
		String output = "Welcome  : " + msg;
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/username/{username : [a-zA-Z][a-zA-Z_0-9]}")
	// {" variable-name [ ":" regular-expression ] "}
	public Response getUserByUserName(@PathParam("username") String username){
		return Response.status(200)
		        .entity("getUserByUserName is called, username : " + username).build();
	}

	@GET
	@Path("/date/{year}/{month}/{day}")
	public Response getUserHistory(
	        @PathParam("year") int year,
	        @PathParam("month") int month,
	        @PathParam("day") int day){

		String date = year + "/" + month + "/" + day;

		return Response.status(200)
		        .entity("getUserHistory is called, year/month/day : " + date)
		        .build();
	}

	@GET
	@Path("/query")
	public Response getUsers(
	        @QueryParam("from") int from,
	        @QueryParam("to") int to,
	        @QueryParam("orderBy") List<String> orderBy){

		return Response
		        .status(200)
		        .entity("getUsers is called, from : " + from + ", to : " + to
		                + ", orderBy" + orderBy.toString()).build();

	}

	@GET
	@Path("/matrix/{year}")
	public Response getBooks(@PathParam("year") String year,
	        @MatrixParam("author") String author,
	        @MatrixParam("country") String country){

		return Response
		        .status(200)
		        .entity("getBooks is called, year : " + year
		                + ", author : " + author + ", country : " + country)
		        .build();

	}

	@Path("smooth")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Ebook smooth(
	        @DefaultValue("blue") @QueryParam("min-color") String minColor,
	        @DefaultValue("green") @QueryParam("max-color") String maxColor,
	        @DefaultValue("red") @QueryParam("last-color") String lastColor){
		System.out.println(minColor + " : " + maxColor + " : " + lastColor);
		return new Ebook();
	}

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrackInJSON(){
		Track track = new Track();
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");
		return track;
	}

}
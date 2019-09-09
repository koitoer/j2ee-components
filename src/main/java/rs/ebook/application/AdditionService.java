package rs.ebook.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;

@ApplicationPath("jax")
@Path("rs")
public class AdditionService extends Application {

	@GET
	@Path("/add/{num1}/{num2}")
	public String addp(@PathParam("num1") int num, @PathParam("num2") int num2){
		return "" + (num + num2);
	}

	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(AdditionService.class);
		return s;
	}
}
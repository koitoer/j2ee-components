package rs.ebook.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import com.koitoer.jax.rs.ebook.service.EbookResourceImpl;
import com.koitoer.jax.rs.helloworld.HelloWorldResource;

@ApplicationPath(value = "/services")
public class EbookApplication extends javax.ws.rs.core.Application {

	private final Set<Object> singletons = new HashSet<Object>();
	private final Set<Class<?>> empty = new HashSet<Class<?>>();

	public EbookApplication() {
		System.out.println("Building Application");
		singletons.add(new EbookResourceImpl());
		singletons.add(new HelloWorldResource());
	}

	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(HelloWorldResource.class);
		return s;
	}

	@Override
	public Set<Object> getSingletons(){
		return singletons;
	}
}

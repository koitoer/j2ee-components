package webservices.chapter2.service3.ops;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;

import com.koitoer.webservices.chapter2.service3.Adage;
import com.koitoer.webservices.chapter2.service3.Adages;

import java.util.List;

public class JsonAllResource extends ServerResource {
    public JsonAllResource() { }

    @Get
    public Representation toJson() {
	List<Adage> list = Adages.getList();

	// Generate the JSON representation.
	JsonRepresentation json = null;
	try {
	    json = new JsonRepresentation(new StringRepresentation(list.toString()));
	}
        catch(Exception e) { }
	return json;
    }
}



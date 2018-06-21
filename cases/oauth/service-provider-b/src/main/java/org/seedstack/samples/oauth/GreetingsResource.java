package org.seedstack.samples.oauth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.seedstack.seed.security.RequiresPermissions;

@Path("/greetings/{name}")
public class GreetingsResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RequiresPermissions("sayHello")
    public String sayHello(@QueryParam("name") String name) {
        return "Hello " + name + "!";
    }
}

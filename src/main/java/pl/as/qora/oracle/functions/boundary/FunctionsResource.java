package pl.as.qora.oracle.functions.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import pl.as.qora.oracle.functions.control.FunctionsService;
import pl.as.qora.oracle.objects.entity.Objects;

@Path("functions")
public class FunctionsResource {
    
    @Inject
    FunctionsService functionsService;

    @GET
    public Collection<Objects> functions(@QueryParam("user") String user) {
        return functionsService.userFunctions(user);
    }
}

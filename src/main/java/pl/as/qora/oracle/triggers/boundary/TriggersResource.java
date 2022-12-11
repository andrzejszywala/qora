package pl.as.qora.oracle.triggers.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import pl.as.qora.oracle.objects.entity.Objects;
import pl.as.qora.oracle.triggers.control.TriggersService;

@Path("triggers")
public class TriggersResource {
    
    @Inject
    TriggersService triggersService;

    @GET
    public Collection<Objects> triggers(@QueryParam("user") String user) {
        return triggersService.userTriggers(user);
    }
}

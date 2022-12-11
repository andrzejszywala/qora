package pl.as.qora.oracle.procedures.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import pl.as.qora.oracle.objects.entity.Objects;
import pl.as.qora.oracle.procedures.control.ProceduresService;

@Path("procedures")
public class ProceduresResource {
    
    @Inject
    ProceduresService proceduresService;

    @GET
    public Collection<Objects> procedures(@QueryParam("user") String user) {
        return proceduresService.userProcedures(user);
    }
}

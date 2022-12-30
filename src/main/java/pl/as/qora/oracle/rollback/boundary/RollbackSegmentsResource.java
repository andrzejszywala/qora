package pl.as.qora.oracle.rollback.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import pl.as.qora.oracle.roles.control.RolesService;
import pl.as.qora.oracle.roles.entity.Role;
import pl.as.qora.oracle.rollback.control.RollbackSegmentsService;
import pl.as.qora.oracle.rollback.entity.RollbackSeg;

@Path("rollback_segments")
public class RollbackSegmentsResource {

    @Inject
    RollbackSegmentsService rollbackSegmentsService;

    @GET
    public Collection<RollbackSeg> rollbackSegments() {
        return rollbackSegmentsService.rollbackSegments();
    }
}

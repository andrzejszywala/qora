package pl.as.qora.oracle.sequence.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import pl.as.qora.oracle.sequence.control.SequencesService;
import pl.as.qora.oracle.sequence.entity.Sequence;

@Path("sequences")
public class SequencesResource {
    
    @Inject
    SequencesService sequencesService;

    @GET
    public Collection<Sequence> sequences(@QueryParam("user") String user) {
        return sequencesService.userSequences(user);
    }
}

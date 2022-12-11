package pl.as.qora.oracle.indexes.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import pl.as.qora.oracle.indexes.control.IndexesService;

@Path("indexes")
public class IndexesResource {
    
    @Inject
    IndexesService indexesService;

    @GET
    public Collection<pl.as.qora.oracle.indexes.entity.Index> tables(@QueryParam("user") String user) {
        return indexesService.userIndexes(user);
    }
}

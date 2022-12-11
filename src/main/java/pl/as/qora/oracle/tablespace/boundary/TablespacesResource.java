package pl.as.qora.oracle.tablespace.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import pl.as.qora.oracle.tablespace.control.TablespacesService;
import pl.as.qora.oracle.tablespace.entity.Tablespace;

@Path("tablespaces")
public class TablespacesResource {

    @Inject
    TablespacesService tablespacesService;

    @GET
    public Collection<Tablespace> tablespaces() {
        return tablespacesService.tablespaces();
    }
}

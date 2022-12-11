package pl.as.qora.oracle.tables.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import pl.as.qora.oracle.tables.control.TablesService;
import pl.as.qora.oracle.tables.entity.Tables;

@Path("tables")
public class TablesResource {
    
    @Inject
    TablesService tablesService;

    @GET
    public Collection<Tables> tables(@QueryParam("user") String user) {
        return tablesService.userTables(user);
    }
}

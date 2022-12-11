package pl.as.qora.oracle.roles.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import pl.as.qora.oracle.roles.control.RolesService;
import pl.as.qora.oracle.roles.entity.Role;

@Path("roles")
public class RolesResource {

    @Inject
    RolesService rolesService;

    @GET
    public Collection<Role> roles() {
        return rolesService.roles();
    }
}

package pl.as.qora.oracle.users.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import pl.as.qora.oracle.users.control.UsersService;
import pl.as.qora.oracle.users.entity.User;

@Path("users")
public class UsersResource {

    @Inject
    UsersService usersService;

    @GET
    public Collection<User> users() {
        return usersService.users();
    }
}

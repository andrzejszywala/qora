package pl.as.qora.oracle.users.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.panache.common.Sort;
import pl.as.qora.oracle.users.entity.User;

@ApplicationScoped
public class UsersService {

    public Collection<User> users() {
        return User.listAll(Sort.by("userName"));
    }

	
}

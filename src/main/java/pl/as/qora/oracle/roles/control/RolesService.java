package pl.as.qora.oracle.roles.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.panache.common.Sort;
import pl.as.qora.oracle.roles.entity.Role;

@ApplicationScoped
public class RolesService {

    public Collection<Role> roles() {
        return Role.listAll(Sort.by("role"));
    }

	
}

package pl.as.qora.oracle.profiles.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.panache.common.Sort;
import pl.as.qora.oracle.profiles.entity.Profile;

@ApplicationScoped
public class ProfilesService {

    public Collection<Profile> profiles() {
        return Profile.listAll(Sort.by("profile"));
    }

	
}

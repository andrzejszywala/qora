package pl.as.qora.oracle.profiles.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import pl.as.qora.oracle.profiles.control.ProfilesService;
import pl.as.qora.oracle.profiles.entity.Profile;

@Path("profiles")
public class ProfilesResource {

    @Inject
    ProfilesService profilesService;

    @GET
    public Collection<Profile> profiles() {
        return profilesService.profiles();
    }
}

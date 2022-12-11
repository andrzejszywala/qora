package pl.as.qora.oracle.packages.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import pl.as.qora.oracle.objects.entity.Objects;
import pl.as.qora.oracle.packages.control.PackagesService;

@Path("packages")
public class PackagesResource {
    
    @Inject
    PackagesService packagesService;

    @GET
    public Collection<Objects> packages(@QueryParam("user") String user) {
        return packagesService.userPackages(user);
    }
}

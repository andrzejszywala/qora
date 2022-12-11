package pl.as.qora.oracle.views.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import pl.as.qora.oracle.views.control.ViewsService;
import pl.as.qora.oracle.views.entity.View;

@Path("views")
public class ViewsResource {
    
    @Inject
    ViewsService viewsService;

    @GET
    public Collection<View> tables(@QueryParam("user") String user) {
        return viewsService.userViews(user);
    }
}

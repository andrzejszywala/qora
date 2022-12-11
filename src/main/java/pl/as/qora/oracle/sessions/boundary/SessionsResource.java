package pl.as.qora.oracle.sessions.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import pl.as.qora.oracle.sessions.control.SessionsService;
import pl.as.qora.oracle.sessions.entity.Session;

@Path("sessions")
public class SessionsResource {
    
    @Inject
    SessionsService sessionsService;

    @GET
    public Collection<Session> sessions() {
        return sessionsService.sessions();
    }
}

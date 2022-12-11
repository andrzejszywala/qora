package pl.as.qora.oracle.sessions.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.panache.common.Sort;
import pl.as.qora.oracle.sessions.entity.Session;

@ApplicationScoped
public class SessionsService {
    
    public Collection<Session> sessions() {
        return Session.listAll(Sort.by("name"));
    }
}

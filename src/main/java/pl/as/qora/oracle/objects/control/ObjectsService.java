package pl.as.qora.oracle.objects.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import pl.as.qora.oracle.objects.entity.Objects;

@ApplicationScoped
public class ObjectsService {
    
    public Collection<Objects> userObjects(String user, String type) {
        return Objects.findByUserAndType(user, type);
    }
    
    public Objects objectByUserNameAndType(String user, String name, String type) {
        return Objects.findByUserNameAndType(user, name, type);
    }
}

package pl.as.qora.oracle.procedures.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pl.as.qora.oracle.objects.control.ObjectsService;
import pl.as.qora.oracle.objects.entity.ObjectTypes;
import pl.as.qora.oracle.objects.entity.Objects;

@ApplicationScoped
public class ProceduresService {
    
	@Inject
	ObjectsService objectsService;
	
    public Collection<Objects> userProcedures(String user) {
        return objectsService.userObjects(user, ObjectTypes.PROCEDURE);
    }
    
}

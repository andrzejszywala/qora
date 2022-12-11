package pl.as.qora.oracle.functions.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pl.as.qora.oracle.objects.control.ObjectsService;
import pl.as.qora.oracle.objects.entity.ObjectTypes;
import pl.as.qora.oracle.objects.entity.Objects;

@ApplicationScoped
public class FunctionsService {
    
	@Inject
	ObjectsService objectsService;
	
    public Collection<Objects> userFunctions(String user) {
        return objectsService.userObjects(user, ObjectTypes.FUNCTION);
    }
    
}

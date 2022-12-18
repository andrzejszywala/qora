package pl.as.qora.oracle.packages.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pl.as.qora.oracle.objects.control.ObjectsService;
import pl.as.qora.oracle.objects.entity.ObjectTypes;
import pl.as.qora.oracle.objects.entity.Objects;

@ApplicationScoped
public class PackagesService {
    
	@Inject
	ObjectsService objectsService;
	
    public Collection<Objects> userPackages(String user) {
        return objectsService.userObjects(user, ObjectTypes.PACKAGE);
    }
    
	public Objects packageBody(String user, String name) {
		return objectsService.objectByUserNameAndType(user, name, ObjectTypes.PACKAGE_BODY);
	}
    
}

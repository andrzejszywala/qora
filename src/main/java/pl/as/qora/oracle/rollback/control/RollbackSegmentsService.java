package pl.as.qora.oracle.rollback.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.panache.common.Sort;
import pl.as.qora.oracle.roles.entity.Role;
import pl.as.qora.oracle.rollback.entity.RollbackSeg;

@ApplicationScoped
public class RollbackSegmentsService {

    public Collection<RollbackSeg> rollbackSegments() {
        return RollbackSeg.listAll(Sort.by("segmentName"));
    }

	
}

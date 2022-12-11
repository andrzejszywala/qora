package pl.as.qora.oracle.tablespace.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.panache.common.Sort;
import pl.as.qora.oracle.tablespace.entity.Tablespace;

@ApplicationScoped
public class TablespacesService {

    public Collection<Tablespace> tablespaces() {
        return Tablespace.listAll(Sort.by("tablespaceName"));
    }

	
}

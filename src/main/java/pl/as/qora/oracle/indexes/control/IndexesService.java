package pl.as.qora.oracle.indexes.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import pl.as.qora.oracle.indexes.entity.Index;

@ApplicationScoped
public class IndexesService {
    
    public Collection<Index> userIndexes(String user) {
        return Index.findByUser(user);
    }
}

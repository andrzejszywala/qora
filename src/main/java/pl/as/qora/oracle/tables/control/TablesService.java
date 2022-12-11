package pl.as.qora.oracle.tables.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import pl.as.qora.oracle.tables.entity.Tables;

@ApplicationScoped
public class TablesService {
    
    public Collection<Tables> userTables(String user) {
        return Tables.findByUser(user);
    }
}

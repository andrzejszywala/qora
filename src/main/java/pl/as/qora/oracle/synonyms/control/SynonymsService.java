package pl.as.qora.oracle.synonyms.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import pl.as.qora.oracle.synonyms.entity.Synonym;

@ApplicationScoped
public class SynonymsService {
    
    public Collection<Synonym> userSynonyms(String user) {
        return Synonym.findByUser(user);
    }
}

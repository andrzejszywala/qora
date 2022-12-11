package pl.as.qora.oracle.synonyms.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import pl.as.qora.oracle.synonyms.control.SynonymsService;
import pl.as.qora.oracle.synonyms.entity.Synonym;

@Path("synonyms")
public class SynonymsResource {
    
    @Inject
    SynonymsService synonymsService;

    @GET
    public Collection<Synonym> synonyms(@QueryParam("user") String user) {
        return synonymsService.userSynonyms(user);
    }
}

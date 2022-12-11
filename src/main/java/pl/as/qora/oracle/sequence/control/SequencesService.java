package pl.as.qora.oracle.sequence.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import pl.as.qora.oracle.sequence.entity.Sequence;

@ApplicationScoped
public class SequencesService {
    
    public Collection<Sequence> userSequences(String user) {
        return Sequence.findByUser(user);
    }
}

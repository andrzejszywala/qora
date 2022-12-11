package pl.as.qora.oracle.views.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import pl.as.qora.oracle.views.entity.View;

@ApplicationScoped
public class ViewsService {
    
    public Collection<View> userViews(String user) {
        return View.findByUser(user);
    }

}

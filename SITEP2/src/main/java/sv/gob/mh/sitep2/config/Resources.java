package sv.gob.mh.sitep2.config;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.java.Log;


/**
 *
 * @author Sonia
 */
@Singleton
@Log
public class Resources implements Serializable {
    
    private static final long serialVersionUID = -1521198635998390564L;

    @Produces
        @PersistenceContext(unitName = "sitepPU")
        private EntityManager em;

    @Produces
        @RequestScoped
        public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    @Produces
        public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

  
}

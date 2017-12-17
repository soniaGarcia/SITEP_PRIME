/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mh.sitep.facade;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.mh.sitep.Profesion;
import sv.gob.mh.sitep.Profesion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sv.gob.mh.sitep.Cliente;

/**
 *
 * @author Sonia Garcia
 */
@Stateless
public class ProfesionFacade extends AbstractFacade<Profesion> {

    @PersistenceContext(unitName = "sv.gob.mh_SITEP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesionFacade() {
        super(Profesion.class);
    }

    public boolean isClienteCollectionEmpty(Profesion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Profesion> profesion = cq.from(Profesion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(profesion, entity), cb.isNotEmpty(profesion.get(Profesion_.clienteCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Cliente> findClienteCollection(Profesion entity) {
        Profesion mergedEntity = this.getMergedEntity(entity);
        Collection<Cliente> clienteCollection = mergedEntity.getClienteCollection();
        clienteCollection.size();
        return clienteCollection;
    }
    
}

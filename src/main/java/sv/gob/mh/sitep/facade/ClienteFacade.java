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
import sv.gob.mh.sitep.Cliente;
import sv.gob.mh.sitep.Cliente_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sv.gob.mh.sitep.Direccion;
import sv.gob.mh.sitep.Profesion;

/**
 *
 * @author Sonia Garcia
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "sv.gob.mh_SITEP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    public boolean isDireccionCollectionEmpty(Cliente entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cliente> cliente = cq.from(Cliente.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cliente, entity), cb.isNotEmpty(cliente.get(Cliente_.direccionCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Direccion> findDireccionCollection(Cliente entity) {
        Cliente mergedEntity = this.getMergedEntity(entity);
        Collection<Direccion> direccionCollection = mergedEntity.getDireccionCollection();
        direccionCollection.size();
        return direccionCollection;
    }

    public boolean isProfesionIdEmpty(Cliente entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cliente> cliente = cq.from(Cliente.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cliente, entity), cb.isNotNull(cliente.get(Cliente_.profesionId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Profesion findProfesionId(Cliente entity) {
        return this.getMergedEntity(entity).getProfesionId();
    }
    
}

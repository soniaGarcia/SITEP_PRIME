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
import sv.gob.mh.sitep.Pais;
import sv.gob.mh.sitep.Pais_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sv.gob.mh.sitep.Direccion;
import sv.gob.mh.sitep.Departamento;

/**
 *
 * @author Sonia Garcia
 */
@Stateless
public class PaisFacade extends AbstractFacade<Pais> {

    @PersistenceContext(unitName = "sv.gob.mh_SITEP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaisFacade() {
        super(Pais.class);
    }

    public boolean isDireccionCollectionEmpty(Pais entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Pais> pais = cq.from(Pais.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(pais, entity), cb.isNotEmpty(pais.get(Pais_.direccionCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Direccion> findDireccionCollection(Pais entity) {
        Pais mergedEntity = this.getMergedEntity(entity);
        Collection<Direccion> direccionCollection = mergedEntity.getDireccionCollection();
        direccionCollection.size();
        return direccionCollection;
    }

    public boolean isDepartamentoCollectionEmpty(Pais entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Pais> pais = cq.from(Pais.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(pais, entity), cb.isNotEmpty(pais.get(Pais_.departamentoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Departamento> findDepartamentoCollection(Pais entity) {
        Pais mergedEntity = this.getMergedEntity(entity);
        Collection<Departamento> departamentoCollection = mergedEntity.getDepartamentoCollection();
        departamentoCollection.size();
        return departamentoCollection;
    }
    
}

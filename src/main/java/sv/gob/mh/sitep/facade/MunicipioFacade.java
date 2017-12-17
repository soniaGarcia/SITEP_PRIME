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
import sv.gob.mh.sitep.Municipio;
import sv.gob.mh.sitep.Municipio_;
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
public class MunicipioFacade extends AbstractFacade<Municipio> {

    @PersistenceContext(unitName = "sv.gob.mh_SITEP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }

    public boolean isDireccionCollectionEmpty(Municipio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Municipio> municipio = cq.from(Municipio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(municipio, entity), cb.isNotEmpty(municipio.get(Municipio_.direccionCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Direccion> findDireccionCollection(Municipio entity) {
        Municipio mergedEntity = this.getMergedEntity(entity);
        Collection<Direccion> direccionCollection = mergedEntity.getDireccionCollection();
        direccionCollection.size();
        return direccionCollection;
    }

    public boolean isDepartamentoIdEmpty(Municipio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Municipio> municipio = cq.from(Municipio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(municipio, entity), cb.isNotNull(municipio.get(Municipio_.departamentoId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Departamento findDepartamentoId(Municipio entity) {
        return this.getMergedEntity(entity).getDepartamentoId();
    }
    
}

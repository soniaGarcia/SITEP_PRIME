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
import sv.gob.mh.sitep.Departamento;
import sv.gob.mh.sitep.Departamento_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sv.gob.mh.sitep.Direccion;
import sv.gob.mh.sitep.Pais;
import sv.gob.mh.sitep.Municipio;

/**
 *
 * @author Sonia Garcia
 */
@Stateless
public class DepartamentoFacade extends AbstractFacade<Departamento> {

    @PersistenceContext(unitName = "sv.gob.mh_SITEP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }

    public boolean isDireccionCollectionEmpty(Departamento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Departamento> departamento = cq.from(Departamento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(departamento, entity), cb.isNotEmpty(departamento.get(Departamento_.direccionCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Direccion> findDireccionCollection(Departamento entity) {
        Departamento mergedEntity = this.getMergedEntity(entity);
        Collection<Direccion> direccionCollection = mergedEntity.getDireccionCollection();
        direccionCollection.size();
        return direccionCollection;
    }

    public boolean isPaisIdEmpty(Departamento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Departamento> departamento = cq.from(Departamento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(departamento, entity), cb.isNotNull(departamento.get(Departamento_.paisId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Pais findPaisId(Departamento entity) {
        return this.getMergedEntity(entity).getPaisId();
    }

    public boolean isMunicipioCollectionEmpty(Departamento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Departamento> departamento = cq.from(Departamento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(departamento, entity), cb.isNotEmpty(departamento.get(Departamento_.municipioCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Municipio> findMunicipioCollection(Departamento entity) {
        Departamento mergedEntity = this.getMergedEntity(entity);
        Collection<Municipio> municipioCollection = mergedEntity.getMunicipioCollection();
        municipioCollection.size();
        return municipioCollection;
    }
    
}

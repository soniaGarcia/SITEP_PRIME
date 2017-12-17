/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mh.sitep.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.mh.sitep.Direccion;
import sv.gob.mh.sitep.Direccion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sv.gob.mh.sitep.Cliente;
import sv.gob.mh.sitep.Departamento;
import sv.gob.mh.sitep.Municipio;
import sv.gob.mh.sitep.Pais;

/**
 *
 * @author Sonia Garcia
 */
@Stateless
public class DireccionFacade extends AbstractFacade<Direccion> {

    @PersistenceContext(unitName = "sv.gob.mh_SITEP_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DireccionFacade() {
        super(Direccion.class);
    }

    public boolean isClienteIdEmpty(Direccion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Direccion> direccion = cq.from(Direccion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(direccion, entity), cb.isNotNull(direccion.get(Direccion_.clienteId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cliente findClienteId(Direccion entity) {
        return this.getMergedEntity(entity).getClienteId();
    }

    public boolean isDepartamentoIdEmpty(Direccion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Direccion> direccion = cq.from(Direccion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(direccion, entity), cb.isNotNull(direccion.get(Direccion_.departamentoId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Departamento findDepartamentoId(Direccion entity) {
        return this.getMergedEntity(entity).getDepartamentoId();
    }

    public boolean isMunicipioIdEmpty(Direccion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Direccion> direccion = cq.from(Direccion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(direccion, entity), cb.isNotNull(direccion.get(Direccion_.municipioId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Municipio findMunicipioId(Direccion entity) {
        return this.getMergedEntity(entity).getMunicipioId();
    }

    public boolean isPaisIdEmpty(Direccion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Direccion> direccion = cq.from(Direccion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(direccion, entity), cb.isNotNull(direccion.get(Direccion_.paisId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Pais findPaisId(Direccion entity) {
        return this.getMergedEntity(entity).getPaisId();
    }
    
}

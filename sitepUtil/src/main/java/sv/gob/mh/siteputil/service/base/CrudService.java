package sv.gob.mh.siteputil.service.base;

import com.mysema.query.BooleanBuilder;
import static com.mysema.query.group.GroupBy.groupBy;
import static com.mysema.query.group.GroupBy.list;
import static com.mysema.query.group.GroupBy.map;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadataFactory;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.SimplePath;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.mvel2.MVEL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import sv.gob.mh.sitepcommon.domain.util.BaseEntity;
import sv.gob.mh.sitepcommon.repository.util.BaseRepository;
import sv.gob.mh.sitepcommon.service.util.BaseCrud;

@Getter
@Setter
@Log
public abstract class CrudService<T extends BaseEntity<ID>, ID extends Serializable> extends BaseCrud<T, ID> {

    public final static List<String> IGNORE_FIELDS = Arrays.asList(new String[]{"consolidated"});

    public abstract BaseRepository<T, ID> getRepository();

    //@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public T findOne(ID id) {
        return getRepository().findOne(id);
    }

    public T findSingleAny() {
        EntityPathBase<T> qentity = getQEntity();
        BooleanExpression idpath = null;
        if (Number.class.isAssignableFrom(idclazz)) {
            idpath = new NumberPath(idclazz, PathMetadataFactory.forProperty(qentity, "id")).isNotNull();
        } else {
            idpath = new SimplePath(idclazz, PathMetadataFactory.forProperty(qentity, "id")).isNotNull();
        }
        return newJpaQuery().from(qentity).where(idpath).singleResult(qentity);
    }

    public T findById(ID id) {
        T ret = null;
        EntityPathBase<T> qentity = getQEntity();
        BooleanExpression idpath = null;
        if (Number.class.isAssignableFrom(idclazz)) {
            idpath = new NumberPath(idclazz, PathMetadataFactory.forProperty(qentity, "id")).eq(id);
        } else {
            idpath = new SimplePath(idclazz, PathMetadataFactory.forProperty(qentity, "id")).eq(id);
        }
        List<T> all = newJpaQuery().from(qentity).where(idpath).list(qentity);
        if (all != null && !all.isEmpty()) {
            ret = all.get(0);
        }
        return ret;
    }

    public T findSelected(ID id) {
        return findById(id);
    }

    //@TransactionAttribute(NOT_SUPPORTED)
    public List<T> findAll() {
        return getRepository().findAll();
    }

    //@TransactionAttribute(NOT_SUPPORTED)
    public List<T> findAll(Predicate predicate) {
        // EntityPathBase<T> entity = getQEntity();
        // return newJpaQuery().from(entity).where(predicate).list(entity);
        return (List<T>) getRepository().findAll(predicate);
    }

    //@TransactionAttribute(NOT_SUPPORTED)
    public List<T> findAll(Predicate predicate, Sort sort) {
        return (List<T>) getRepository().findAll(predicate, sort);
    }

    //@TransactionAttribute(NOT_SUPPORTED)
    public Page<T> findAll(Predicate predicate, Pageable pageable) {
        return getRepository().findAll(predicate, pageable);
    }

    public <S extends T> S save(S entity) {
        return getRepository().save(entity);
    }

    public <S extends T> List<S> save(Iterable<S> entities) {
        return getRepository().save(entities);
    }

    public void delete(T entity) {
        beforeDelete(entity);
        getRepository().delete(entity);
    }

    public void beforeDelete(T entity) {

    }

    public void beforeDelete(Iterable<T> iterable) {

    }

    public void delete(Iterable<T> iterable) {
        beforeDelete(iterable);
        getRepository().delete(iterable);
    }

    public long removeOne(T entity) {
        EntityPathBase<T> qentity = getQEntity();
        return newJPADeleteClause().where((new NumberPath(idclazz, PathMetadataFactory.forProperty(qentity, "id")).eq(entity.getId()))).execute();
    }

    public long remove(EntityPath<?> qentity, BooleanExpression bexp) {
        JPADeleteClause jpadelete = new JPADeleteClause(em, qentity);
        return jpadelete.where(bexp).execute();
    }


    public Map<String, Object> vars() {
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put(ENTITY_VAR, getQEntity());
        return vars;
    }

    //@TransactionAttribute(NOT_SUPPORTED)
    public Map<ID, T> findAllAsMap() {
        EntityPathBase<T> entity = getQEntity();
        return (Map<ID, T>) newJpaQuery().from(entity).transform(groupBy(new NumberPath(idclazz, PathMetadataFactory.forProperty(entity, "id"))).as(entity));
    }

    //@TransactionAttribute(NOT_SUPPORTED)
    public <A extends Serializable> Map<A, T> findAllAsMapWithPathAsKey(Path<? super A> path) {
        EntityPathBase<T> entity = getQEntity();
        return (Map<A, T>) newJpaQuery().from(entity).transform(groupBy(path).as(entity));
    }

    //@TransactionAttribute(NOT_SUPPORTED)
    public <A extends Serializable, B extends Serializable> Map<A, List<T>> findAllasMapGroupedByPath(Path<? super A> path, BooleanExpression criteria, Path<? super B> anotherpath) {
        Map<A, List<T>> ret = null;
        EntityPathBase<T> entity = getQEntity();
        JPAQuery oq;
        if (criteria != null) {
            oq = newJpaQuery().from(entity).where(criteria);
        } else {
            oq = newJpaQuery().from(entity);
        }
        Path expr = anotherpath != null ? anotherpath : entity;
        ret = (Map<A, List<T>>) oq.transform(groupBy(path).as(list(expr)));
        return new TreeMap<A, List<T>>(ret);
    }

    public <A extends Serializable, B extends Serializable, C extends Serializable> Map<A, Map<B, T>> findAllGroupedUsingMap(Path<? super A> groupedBy) {
        EntityPathBase<T> entity = getQEntity();
        NumberPath key = new NumberPath(idclazz, PathMetadataFactory.forProperty(entity, "id"));
        return findAllGroupedUsingMap(groupedBy, null, key, null);
    }

    public <A extends Serializable, B extends Serializable, C extends Serializable> Map<A, Map<B, T>> findAllGroupedUsingMap(Path<? super A> groupedBy, Path<B> key) {
        return findAllGroupedUsingMap(groupedBy, null, key, null);
    }

    public <A extends Serializable, B extends Serializable, C extends Serializable> Map<A, Map<B, T>> findAllGroupedUsingMap(Path<? super A> groupedBy, Path<B> key, BooleanExpression criteria) {
        return findAllGroupedUsingMap(groupedBy, null, key, criteria);
    }

    public <A extends Serializable, B extends Serializable, C extends Serializable> Map<A, Map<B, T>> findAllGroupedUsingMap(Path<? super A> groupedBy, Path<? super C> anotherpath, Path<B> key, BooleanExpression criteria) {
        Map<A, Map<B, T>> ret = null;
        EntityPathBase<T> entity = getQEntity();
        JPAQuery oq;
        if (criteria != null) {
            oq = newJpaQuery().from(entity).where(criteria);
        } else {
            oq = newJpaQuery().from(entity);
        }
        Path expr = anotherpath != null ? anotherpath : entity;
        ret = (Map<A, Map<B, T>>) oq.transform(groupBy(groupedBy).as(map(key, expr)));
        return new TreeMap<A, Map<B, T>>(ret);
    }

    public <A extends Serializable> Map<A, List<T>> findAllasMapGroupedByPath(Path<? super A> path) {
        return findAllasMapGroupedByPath(path, null, null);
    }

    public <A extends Serializable> Map<A, List<T>> findAllasMapGroupedByPath(Path<? super A> path, BooleanExpression criteria) {
        return findAllasMapGroupedByPath(path, criteria, null);
    }

    public <A extends Serializable> Map<A, List<T>> findAllasMapGroupedByPath(Path<? super A> path, Path<T> anotherpath) {
        return findAllasMapGroupedByPath(path, null, anotherpath);
    }

    public List<T> findAllByParent(long parentId, Class parentClass) {
        List<T> ret = null;
        if (parentId > 0) {
            String parentField = parentField(clazz, parentClass, IGNORE_FIELDS);
            ret = findAllByParent(parentId, parentField);
        }
        return ret;
    }

    public List<T> findAllByParent(long parentId, String parentField) {
        List<T> ret = null;
        if (parentField != null && !"".equals(parentField.trim())) {
            EntityPathBase<T> entity = getQEntity();
            HashMap<String, Object> vars = new HashMap<String, Object>();
            vars.put("entity", entity);
            Predicate predicate = (Predicate) MVEL.eval("entity." + parentField + ".id.eq(" + parentId + ")", vars);
            BooleanBuilder bb = new BooleanBuilder();
            bb.and(predicate);
            ret = (List<T>) getRepository().findAll(bb);
        }
        return ret;
    }


    public static String parentField(Class childClass, Class parentClass, List<String> ignoreFields) {
        String ret = null;
        if (childClass != null) {
            try {
                BeanInfo bi = Introspector.getBeanInfo(childClass);
                PropertyDescriptor[] properties = bi.getPropertyDescriptors();
                if (properties != null && properties.length > 0) {
                    if (childClass.equals(parentClass)) {
                        ret = "";
                    } else {
                        for (PropertyDescriptor pd : properties) {
                            if (parentClass.isAssignableFrom(pd.getReadMethod().getReturnType())) {
                                String propertyName = pd.getName();
                                if (propertyName != null && !propertyName.trim().equals("") && (ignoreFields != null && !ignoreFields.contains(propertyName))) {
                                    ret = propertyName;
                                    break;
                                }
                            }
                        }
                    }
                }
            } catch (IntrospectionException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
        return ret;

    }

    public void detach(T... entities) {
        for (T t : entities) {
            em.detach(t);
        }
    }

    public void detach(Collection<T> entities) {
        for (T t : entities) {
            em.detach(t);
        }
    }

    protected <T extends BaseEntity> Collection<T> bulkSave(Collection<T> entities) {
        final List<T> savedEntities = new ArrayList<T>(entities.size());
        int count = 0;
        for (T t : entities) {
            savedEntities.add(persistOrMerge(t));
            count++;
            if (count % BATCH_SIZE == 0) {
                em.flush();
                em.clear();
            }
        }

        em.flush();
        em.clear();
        return savedEntities;
    }

    protected <T extends BaseEntity> Collection<T> bulkSave(Collection<T> entities, int size) {
        final List<T> savedEntities = new ArrayList<T>(entities.size());
        int count = 0;
        for (T t : entities) {
            savedEntities.add(persistOrMerge(t));
            count++;
            if (count % size == 0) {
                em.flush();
                em.clear();
            }
        }

        em.flush();
        em.clear();
        return savedEntities;
    }

    private <T extends BaseEntity> T persistOrMerge(T t) {
        if (t.getId() == null) {
            em.persist(t);
            return t;
        } else {
            return em.merge(t);
        }
    }
}

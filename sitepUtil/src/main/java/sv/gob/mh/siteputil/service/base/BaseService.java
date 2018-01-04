package sv.gob.mh.siteputil.service.base;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.NonUniqueResultException;
import com.mysema.query.types.Predicate;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import sv.gob.mh.sitepcommon.domain.util.BaseEntity;
import sv.gob.mh.sitepcommon.util.exception.TechnicalException;
import sv.gob.mh.siteputil.exception.ExceptionMessageManager;
import sv.gob.mh.siteputil.safe.AbsSpec;
import sv.gob.mh.siteputil.safe.GroupedFilter;
import sv.gob.mh.siteputil.safe.ValueHolder;

@Getter
@Setter
public abstract class BaseService<T extends BaseEntity<ID>, ID extends Serializable> extends CrudService<T, ID> {

   public Page<T> findAll(int first, int pageSize, List<Sort.Order> orders, boolean any, boolean custom, Set<ValueHolder> conditions, GroupedFilter groupedFilters, String... raws) {
        Sort sort = new Sort(orders);
        return findAll(first, pageSize, sort, any, custom, conditions, groupedFilters, raws);
    }

    public Page<T> findAll(int first, int pageSize, Sort sort, boolean any, boolean custom, Set<ValueHolder> conditions, GroupedFilter groupedFilters, String... raws) {
        int page = Math.max(first / pageSize, 0);
        PageRequest pageable = new PageRequest(page, pageSize, sort);
        return findAll(pageable, any, custom, conditions, groupedFilters, raws);
    }

    public Page<T> findAll(Pageable pageable, boolean any, boolean custom, Set<ValueHolder> conditions, GroupedFilter groupedFilters, String... raws) {
        BooleanBuilder spec = AbsSpec.builder(vars(), any, custom, conditions, groupedFilters, raws);
        return getRepository().findAll(spec, pageable);
    }
    
    public List<T> findAll(boolean any, boolean custom, Set<ValueHolder> conditions, GroupedFilter groupedFilters, String... raws) {
        BooleanBuilder spec = AbsSpec.builder(vars(), any, custom, conditions, groupedFilters, raws);
        return (List<T>) getRepository().findAll(spec);
    }


    public Class<?> getType(Class cl, Object value) {
        return String.class;
    }

    public T findOne(Predicate predicate) {
        T result = null;
        try {
            try {
                result = getRepository().findOne(predicate);
            } catch (IllegalArgumentException | NonUniqueResultException e) {
                ExceptionMessageManager.throwPredicateError("", e, predicate);
            }
        } catch (TechnicalException e) {
            ExceptionMessageManager.handleException(e);
        }
        return result;
    }

}

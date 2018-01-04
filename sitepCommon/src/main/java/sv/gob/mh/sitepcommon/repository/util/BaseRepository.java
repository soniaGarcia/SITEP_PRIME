package sv.gob.mh.sitepcommon.repository.util;

import java.io.Serializable;
import javax.enterprise.inject.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import sv.gob.mh.sitepcommon.domain.util.BaseEntity;

@NoRepositoryBean
@Alternative
public interface BaseRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>,
        QueryDslPredicateExecutor<T>, Serializable {

}

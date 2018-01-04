package sv.gob.mh.siteputil.safe;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sv.gob.mh.sitepcommon.util.enums.OperatorType;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"operatorType", "valueHolderSet","groupedFilterSet"})
public class GroupedFilter implements Serializable {

    private static final long serialVersionUID = 6085906157892025603L;

    protected OperatorType operatorType = OperatorType.AND;
    protected Set<ValueHolder> valueHolderSet;
    protected Set<GroupedFilter> groupedFilterSet;

    public Set<ValueHolder> getValueHolderSet() {
        if (valueHolderSet == null) {
            valueHolderSet = new HashSet<ValueHolder>(0);
        }
        return valueHolderSet;
    }

    public Set<GroupedFilter> getGroupedFilterSet() {
        if (groupedFilterSet == null) {
            groupedFilterSet = new HashSet<GroupedFilter>();
        }
        return groupedFilterSet;
    }

    public boolean isEmpty() {
        return groupedFilterSet == null || groupedFilterSet.isEmpty();
    }

    public void addValueHolder(ValueHolder valueHolder) {
        getValueHolderSet().add(valueHolder);
    }

    public void clear() {
        getValueHolderSet().clear();
        getGroupedFilterSet().clear();
    }

    public void add(GroupedFilter groupedFilter) {
        getGroupedFilterSet().add(groupedFilter);
    }

    public BooleanBuilder getBooleanBuilder(Map<String, Object> vars) {
        BooleanBuilder bb = new BooleanBuilder();
        if (groupedFilterSet != null && !groupedFilterSet.isEmpty()) {
            List<Predicate> bbs = new ArrayList<Predicate>();
            for (GroupedFilter gf : groupedFilterSet) {
                bbs.add(gf.getBooleanBuilder(vars));
            }
            Predicate[] abbs = new Predicate[bbs.size()];
            bbs.toArray(abbs);
            if (OperatorType.AND.equals(getOperatorType())) {
                bb.orAllOf(abbs);
            } else if (OperatorType.OR.equals(getOperatorType())) {
                bb.andAnyOf(abbs);
            }
        } else if (OperatorType.AND.equals(getOperatorType())) {
            bb.orAllOf(vhsBb(vars));
        } else if (OperatorType.OR.equals(getOperatorType())) {
            bb.andAnyOf(vhsBb(vars));
        }
        return bb;
    }

    private BooleanBuilder vhsBb(Map<String, Object> vars) {
        BooleanBuilder bb = new BooleanBuilder();
        if (valueHolderSet != null && !valueHolderSet.isEmpty()) {
            for (ValueHolder vh : valueHolderSet) {
                Predicate p = vh.predicate(vars);
                if (OperatorType.AND.equals(vh.getOperatorType())) {
                    bb.and(p);
                } else if (OperatorType.OR.equals(vh.getOperatorType())) {
                    bb.or(p);
                }
            }
        }
        return bb;
    }
}
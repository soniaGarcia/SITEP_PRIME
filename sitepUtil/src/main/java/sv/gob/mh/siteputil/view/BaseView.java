package sv.gob.mh.siteputil.view;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import sv.gob.mh.sitepcommon.domain.util.BaseEntity;
import sv.gob.mh.sitepcommon.util.enums.ViewStatus;
import sv.gob.mh.siteputil.safe.ValueHolder;
import sv.gob.mh.siteputil.service.base.BaseService;


@Getter
@Setter
@Log
public abstract class BaseView<T extends BaseEntity<ID>, ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = -5209996916298822830L;

    public abstract BaseService<T, ID> getService();

    protected ID recordId;
    protected T selectedItem;

    protected int status = 0;
    protected boolean any = false;
    int pageSize = 10;

    protected Class clazz;
    protected Class idclazz;

    protected transient BaseService<T, ID> service;
    protected transient BaseLazyModel<T, ID> lazyModel;

    public BaseView() {
        Type genericSuperclass = null;
        Type[] args = null;
        genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            args = ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments();
        }

        if (args != null && args.length >= 2) {
            clazz = (Class) args[0];
            idclazz = (Class) args[1];
        }
    }

    @PostConstruct
    public void rootInit() {
        init();
        this.lazyModel = loadLazyModel();
    }

    public void init() {
        // to be implemented by children
    }

    public BaseLazyModel<T, ID> loadLazyModel() {
        final BaseView<T, ID> obj = this;
        return new BaseLazyModel<T, ID>(getService()) {
            @Override
            public boolean isAny() {
                return obj.isAny();
            }

//            @Override
//            public Set<ValueHolder> getCustomFilters() {
//                return obj.getCustomFilters();
//            }
            @Override
            public void afterApplyFilter(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters, Page<T> page) {
                obj.afterApplyFilter(first, pageSize, multiSortMeta, filters, page);
            }

            @Override
            public List<SortMeta> sortFirst() {
                return obj.sortFirst();
            }

            @Override
            public List<SortMeta> sortLast() {
                return obj.sortLast();
            }
        };
    }

    protected Set<ValueHolder> getCustomFilters() {
        if (lazyModel.getCustomFilters() == null) {
            lazyModel.setCustomFilters(new HashSet<ValueHolder>());
        }
        return lazyModel.getCustomFilters();
    }

    public void addCustomFilter(boolean b, ValueHolder valueHolder) {
        lazyModel.addCustomFilter(b, valueHolder);
    }

    public ViewStatus getStatus() {
        return ViewStatus.getViewStatus(this.status);
    }

    public void setStatus(ViewStatus tipoEtapa) {
        this.status = tipoEtapa.getCode();
    }

    public void onRowSelect(SelectEvent event) {
        selectedItem = getService().findOne(((T) event.getObject()).getId());
    }

    public void prepareNew(ActionEvent e) throws InstantiationException, IllegalAccessException {
        selectedItem = (T) clazz.newInstance();
        setStatus(ViewStatus.NEW);
    }

    public void prepareEdit(ActionEvent e) {
        setStatus(ViewStatus.EDIT);
    }

    public void prepareView(ActionEvent e) {
        setStatus(ViewStatus.VIEW);
    }

    public void reset(ActionEvent e) {
        setStatus(ViewStatus.NONE);
        setSelectedItem(null);
    }

    public void save(ActionEvent e) {
        if (selectedItem != null) {
            getService().save(selectedItem);
            setStatus(ViewStatus.NONE);
        }
    }

    public void delete(ActionEvent e) {
        if (selectedItem != null) {
            getService().delete(selectedItem);
        }
        selectedItem = null;
    }

    public void afterApplyFilter(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters, Page page) {
    }

    public List<SortMeta> sortFirst() {
        return null;
    }

    public List<SortMeta> sortLast() {
        return null;
    }
}

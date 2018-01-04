package sv.gob.mh.siteputil.view;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Callback;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import static org.omnifaces.util.Components.forEachComponent;
import sv.gob.mh.sitepcommon.domain.util.BaseEntity;
import sv.gob.mh.sitepcommon.util.enums.OperationType;
import sv.gob.mh.sitepcommon.util.enums.OperatorType;
import sv.gob.mh.siteputil.safe.GroupedFilter;
import sv.gob.mh.siteputil.safe.PaginatorHelper;
import sv.gob.mh.siteputil.safe.ValueHolder;
import sv.gob.mh.siteputil.service.base.BaseService;

@Getter
@Setter
public class BaseLazyModel<T extends BaseEntity<ID>, ID extends Serializable> extends LazyDataModel<T> {

    private static final long serialVersionUID = -2082521990703879216L;

    protected BaseService<T, ID> service;
    protected transient List<SortMeta> sortingRecord;
    private Set<ValueHolder> customFilters;
    protected ValueHolder newCustomFilter = new ValueHolder(new String(), new String());
    protected ValueHolder selectedFilter;
    protected List<SelectItem> cfilters = new ArrayList<SelectItem>();
    protected List<SelectItem> coptions = new ArrayList<SelectItem>();
    protected String filterType;
    protected Class<T> clazz;
    protected Class<ID> idclazz;
    protected Class<ID> entityClass;
    protected GroupedFilter groupedFilters;
    protected boolean any = true;
    protected String rowsPerPage = "";

    public BaseLazyModel(BaseService<T, ID> service, Class entityClass) {
        this(service);
        this.entityClass = entityClass;
    }

    public BaseLazyModel(BaseService<T, ID> service) {
        this.service = service;
        this.clazz = service.getClazz();
        this.idclazz = service.getIdclazz();
        setNewCustomFilter(new ValueHolder(1, new String(), new String(), new String(), new String(), new String(), null, null));
    }

    @Override
    public void setRowCount(int rowCount) {
        super.setRowCount(rowCount);
        rowsPerPage = rowsPerPage(rowCount);
    }

    public static String rowsPerPage(int count) {
        Set<String> ret = new LinkedHashSet<String>();
        int factor = 5;
        int i = 0;
        while (i <= count) {
            i += factor;
            if (i < count) {
                ret.add("" + i);
            } else if (i >= count) {
                ret.add("" + count);
            }
            if (i >= 20 && i < 50) {
                factor = 10;
            } else if (i >= 50 && i < 100) {
                factor = 50;
            } else if (i >= 100 && i < 500) {
                factor = 100;
            } else if (i >= 500 && i < 1000) {
                factor = 500;
            } else if (i >= 1000 && i < 10000) {
                factor = 1000;
            } else if (i >= 10000) {
                factor = 10000;
            }
        }
        return ret.toString().replaceAll("\\[", "").replaceAll("\\]", "");
    }

    @Override
    public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        Page<T> page = finder(first, pageSize, multiSortMeta, filters);
        setRowCount((int) page.getTotalElements());
        afterApplyFilter(first, pageSize, multiSortMeta, filters, page);
        return page.getContent();

    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<SortMeta> multiSortMeta = new ArrayList<SortMeta>();
        List<SortMeta> sortFirst = sortFirst();
        if (sortFirst != null && !sortFirst.isEmpty()) {
            multiSortMeta.addAll(sortFirst);
        }
        if (sortField != null && sortOrder != null) {
            String[] arrsorts = sortField.split("\\,");
            if (arrsorts != null && arrsorts.length > 0) {
                for (String sort : arrsorts) {
                    SortMeta sortMeta = new SortMeta();
                    sortMeta.setSortField(sort);
                    sortMeta.setSortOrder(sortOrder);
                    multiSortMeta.add(sortMeta);
                }
            }
        }
        List<SortMeta> sortLast = sortLast();
        if (sortLast != null && !sortLast.isEmpty()) {
            multiSortMeta.addAll(sortLast);
        }

        Page<T> page = finder(first, pageSize, multiSortMeta, filters);
        setRowCount((int) page.getTotalElements());
        afterApplyFilter(first, pageSize, multiSortMeta, filters, page);
        return page.getContent();
    }

    protected Page<T> finder(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        if (multiSortMeta != null) {
            sortingRecord = multiSortMeta;
        }
        Pageable pageable = PaginatorHelper.pageable(first, pageSize, sortingRecord);
        Set<ValueHolder> conditions = PaginatorHelper.transformer(generalSearchFields(), filters, entityClass);
        if (conditions == null) {
            conditions = new HashSet<ValueHolder>();
        }
        conditions.addAll(getCustomFilters());
        return service.findAll(pageable, isAny(), isCustomFilter(), conditions, getGroupedFilters());
    }

    @Override
    public T getRowData(String rowKey) {
        T ret = null;
        if (service != null) {
            ID recid = null;
            Class idclass = service.getIdclazz();
            if (idclass.equals(String.class)) {
                recid = (ID) rowKey;
            } else if (idclass.equals(Long.class)) {
                recid = (ID) new Long(rowKey);
            } else if (idclass.equals(Integer.class)) {
                recid = (ID) new Integer(rowKey);
            }

            ret = service.findOne(recid);
        }
        return ret;
    }

    @Override
    public ID getRowKey(T element) {
        return element.getId();
    }

    public List<String> generalSearchFields() {
        return new ArrayList<String>();
    }

    public List<String> FixedFields() {
        return new ArrayList<String>();
    }

    public Set<ValueHolder> getCustomFilters() {
        if (customFilters == null) {
            customFilters = new HashSet<ValueHolder>();
        }
        return customFilters;
    }

    public void addCustomFilter() {
        addCustomFilter(newCustomFilter);
    }

    public void addCustomFilter(ValueHolder filter) {
        getCustomFilters().add(filter);
    }

    public void addCustomFilters(Collection<ValueHolder> filters) {
        getCustomFilters().addAll(filters);
    }

    public void addCustomFilter(boolean clear, ValueHolder filter) {
        if (clear) {
            clearCustomFilters();
        }
        addCustomFilter(filter);
    }

    public void clearCustomFilters() {
        getCustomFilters().clear();
    }

    public void removeCustomFilter() {
        removeCustomFilter(selectedFilter);
    }

    public void removeCustomFilter(ValueHolder filter) {
        if (getCustomFilters().contains(filter)) {
            getCustomFilters().remove(filter);
        }
    }

    public void removeFilter(Integer id) {
        if (id != null) {
            setSelectedFilter(new ValueHolder(id, null, null, null, null, null, null, null));
            removeCustomFilter();
        }
    }

    public void createFilter(final String tablename) throws NoSuchFieldException {
        UIComponent component = null;
        cfilters = new ArrayList<SelectItem>();

        final List<List<UIColumn>> lista = new ArrayList<List<UIColumn>>();
        forEachComponent().fromRoot(component).invoke(new Callback.WithArgument<UIComponent>() {
            @Override
            public void invoke(UIComponent component) {
                if (component.getId() != null && !component.getId().isEmpty() && component.getId().trim().equals(tablename)) {
                    DataTable datatable = (DataTable) component.findComponent(tablename);
                    Map<String, Object> attrs = datatable.getAttributes();
                    lista.add(datatable.getColumns());
                }
            }
        });
        if (!lista.isEmpty()) {
            for (UIColumn componen : lista.get(0)) {
                ValueExpression filterByExp = componen.getValueExpression("filterBy");
                if (filterByExp != null) {
                    String sfilterByExp = filterByExp.getExpressionString();
                    String[] path = expressionToPath(sfilterByExp);
                    if (path != null && path.length > 0) {
                        String keyStr = path[0];
                        int inicio = keyStr.indexOf(":") + 1;
                        String label = componen.getHeaderText();
                        String skey = keyStr.substring(inicio, keyStr.length());
                        cfilters.add(new SelectItem(skey, label, keyStr.substring(inicio, keyStr.length()), false));
                    }
                }
            }
        }
        setcoptionsValues(cfilters.get(0).getValue().toString().trim());
    }

    public static String[] expressionToPath(String exp) {
        String[] ret = null;
        String attribute = "";
        String path = "";
        if (exp != null && !exp.trim().equals("")) {
            attribute = exp.replaceAll("\\#\\{", "").replaceAll("\\}", "");
            int pointPosition = attribute.indexOf('.');
            if (pointPosition > 0) {
                path = attribute.substring(pointPosition + 1, attribute.length());
                attribute = attribute.substring(0, pointPosition);
            }
        }
        if (path == null || path.trim().equals("")) {
            ret = new String[]{attribute};
        } else {
            ret = new String[]{path, attribute};
        }
        return ret;
    }

    public static Class attributeType(Class clazz, String attr) throws NoSuchFieldException {
        List<Class> dateClasses = Arrays.asList(new Class[]{java.util.Date.class, java.util.Calendar.class});
        Class ret = null;
        if (attr != null) {
            if (attr.contains(".")) {
                String obj = attr.substring(0, attr.indexOf('.'));
                String attrib = attr.substring(attr.indexOf('.') + 1, attr.length());
                Field f = getField(clazz, obj);
                Field[] ff = f.getType().getDeclaredFields();
                if (ff != null && !dateClasses.contains(f.getType())) {
                    ret = attributeType(f.getType(), attrib);
                } else {
                    ret = f.getType();
                }
            } else {
                Field f = getField(clazz, attr);
                if (f != null) {
                    ret = f.getType();
                }
            }
        }
        return ret;
    }

    public static Field getField(Class clazz, String fname) {
        Field ret = null;
        if (clazz != null && fname != null && !fname.trim().equals("")) {
            List<Field> fs = allFields(clazz);
            if (fs != null && !fs.isEmpty()) {
                for (Field f : fs) {
                    if (f.getName() != null && f.getName().trim().equalsIgnoreCase(fname)) {
                        ret = f;
                        break;
                    }
                }
            }
        }
        return ret;
    }

    public static List<Field> allFields(Class clazz) {
        List<Field> fs = new ArrayList<Field>(Arrays.asList(clazz.getDeclaredFields()));
        if (clazz.getSuperclass() != null) {
            fs.addAll(allFields(clazz.getSuperclass()));
        }
        return fs;
    }

    public OperationType[] getOperationTypes() {
        return OperationType.values();
    }

    public OperatorType[] getOperatorTypes() {
        return OperatorType.values();
    }

    public void capturarFiltro() throws NoSuchFieldException {
        if (cfilters != null && !cfilters.isEmpty()) {
            for (SelectItem cfilter : cfilters) {
                if (cfilter.getValue().equals(getNewCustomFilter().getName())) {
                    getNewCustomFilter().setLabel(cfilter.getLabel().trim());
                }
            }
        }
        if (getNewCustomFilter() != null) {
            addCustomFilter();
            setNewCustomFilter(new ValueHolder(getCustomFilters().size() + 1, new String(), new String(), new String(), new String(), new String(), null, null));
            filterType = attributeType(cfilters.get(0).getValue().toString().trim());
            setcoptionsValues(cfilters.get(0).getValue().toString().trim());
        }
    }

    public void onCamposChange(AjaxBehaviorEvent event) throws NoSuchFieldException {
        SelectOneMenu component1 = (SelectOneMenu) event.getComponent();
        setcoptionsValues(component1.getValue().toString().trim());
    }

    public void setcoptionsValues(String selectedOption) throws NoSuchFieldException {
        filterType = attributeType(selectedOption);
        coptions = new ArrayList<SelectItem>();
        for (Iterator it = OperationType.getOptionsForType(filterType).iterator(); it.hasNext();) {
            OperationType type = (OperationType) it.next();
            SelectItem itemTemp = new SelectItem(type.getCode(), type.getDescription(), type.getDescription());
            coptions.add(itemTemp);
        }
    }

    public void clearFilter() {
        clearCustomFilters();
        clearGroupedFilters();
        cfilters.clear();
        coptions.clear();
        loadLazyModelCustom();
    }

    public void updateFilter(ValueHolder selected) {
        selected.setOperatorType(getNewCustomFilter().getOperatorType());
        removeCustomFilter(selected);
        addCustomFilter(selected);
    }

    public String attributeType(String attr) throws NoSuchFieldException {
        String ret = null;
        Class type = attributeType(clazz, attr);
        if (type != null) {
            ret = type.getSimpleName();
        }
        return ret;
    }

    public void load() {
        BaseLazyModel<T, ID> lm = loadLazyModelCustom();
    }

    public BaseLazyModel<T, ID> loadLazyModelCustom() {
        return new BaseLazyModel<T, ID>(getService()) {
            @Override
            public boolean isCustomFilter() {
                return true;
            }
        };
    }

    @Override
    public int getPageSize() {
        return 10;
    }

    public boolean isCustomFilter() {
        return customFilters != null && !customFilters.isEmpty();
    }

    public void addGroupedFilters(GroupedFilter pgroupedFilters) {
        if (pgroupedFilters != null) {
            getGroupedFilters().add(pgroupedFilters);
        }
    }

    public void addGroupedFilter(GroupedFilter groupedFilter) {
        getGroupedFilters().add(groupedFilter);
    }

    public GroupedFilter getGroupedFilters() {
        if (groupedFilters == null) {
            groupedFilters = new GroupedFilter();
        }
        return groupedFilters;
    }

    public void clearGroupedFilters() {
        getGroupedFilters().clear();
    }

    public void afterApplyFilter(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters, Page<T> page) {

    }

    public List<SortMeta> sortFirst() {
        return null;
    }

    public List<SortMeta> sortLast() {
        return null;
    }

}

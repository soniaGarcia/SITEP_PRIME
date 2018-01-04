package sv.gob.mh.sitep2.view;

import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import sv.gob.mh.sitepcommon.domain.Departamento;
import sv.gob.mh.sitepcommon.domain.Pais;
import sv.gob.mh.siteputil.service.DepartamentoService;
import sv.gob.mh.siteputil.service.PaisService;
import sv.gob.mh.siteputil.service.base.BaseService;
import sv.gob.mh.siteputil.view.BaseView;


@Named(value= "departamentoView")
@ViewScoped
@Getter
@Setter
public class DepartamentoView extends BaseView<Departamento, Long> {

    @Inject
    private transient DepartamentoService departamentoService;

    @Override
    public BaseService<Departamento, Long> getService() {
        return departamentoService;
    }

    @Inject
     private transient PaisService paisService;    
    
    List<Pais> paises = new ArrayList<>();
    
    @Override
    public void prepareView(ActionEvent e) {
        
        super.prepareView(e);

    }
    @Override
     public void init(){
     paises = paisService.findAll();
     } 
}
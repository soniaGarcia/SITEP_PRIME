package sv.gob.mh.sitep2.view;

import javax.faces.event.ActionEvent;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import sv.gob.mh.sitepcommon.domain.Direccion;
import sv.gob.mh.siteputil.service.DireccionService;
import sv.gob.mh.siteputil.service.base.BaseService;
import sv.gob.mh.siteputil.view.BaseView;


@Named(value= "direccionView")
@ViewScoped
@Getter
@Setter
public class DireccionView extends BaseView<Direccion, Long> {

    @Inject
    private transient DireccionService direccionService;

    @Override
    public BaseService<Direccion, Long> getService() {
        return direccionService;
    }

    @Override
    public void prepareView(ActionEvent e) {
        super.prepareView(e);

    }
}
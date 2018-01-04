package sv.gob.mh.sitep2.view;

import javax.faces.event.ActionEvent;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import sv.gob.mh.sitepcommon.domain.Municipio;
import sv.gob.mh.siteputil.service.MunicipioService;
import sv.gob.mh.siteputil.service.base.BaseService;
import sv.gob.mh.siteputil.view.BaseView;



@Named(value= "municipioView")
@ViewScoped
@Getter
@Setter
public class MunicipioView extends BaseView<Municipio, Long> {

    @Inject
    private transient MunicipioService municipioService;

    @Override
    public BaseService<Municipio, Long> getService() {
        return municipioService;
    }

    @Override
    public void prepareView(ActionEvent e) {
        super.prepareView(e);

    }
}
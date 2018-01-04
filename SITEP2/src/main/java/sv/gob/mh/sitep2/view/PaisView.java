package sv.gob.mh.sitep2.view;

import javax.faces.event.ActionEvent;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import sv.gob.mh.sitepcommon.domain.Pais;
import sv.gob.mh.siteputil.service.PaisService;
import sv.gob.mh.siteputil.service.base.BaseService;
import sv.gob.mh.siteputil.view.BaseView;


@Named(value= "paisView")
@ViewScoped
@Getter
@Setter
public class PaisView extends BaseView<Pais, Long> {

    @Inject
    private transient PaisService paisService;

    @Override
    public BaseService<Pais, Long> getService() {
        return paisService;
    }

    @Override
    public void prepareView(ActionEvent e) {
        super.prepareView(e);

    }
}
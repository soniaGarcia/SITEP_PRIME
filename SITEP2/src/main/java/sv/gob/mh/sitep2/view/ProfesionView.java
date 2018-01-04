package sv.gob.mh.sitep2.view;

import javax.faces.event.ActionEvent;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import sv.gob.mh.sitepcommon.domain.Profesion;
import sv.gob.mh.siteputil.service.ProfesionService;
import sv.gob.mh.siteputil.service.base.BaseService;
import sv.gob.mh.siteputil.view.BaseView;


@Named(value= "profesionView")
@ViewScoped
@Getter
@Setter
public class ProfesionView extends BaseView<Profesion, Long> {

    @Inject
    private transient ProfesionService profesionService;

    @Override
    public BaseService<Profesion, Long> getService() {
        return profesionService;
    }

    @Override
    public void prepareView(ActionEvent e) {
        super.prepareView(e);

    }
}
package sv.gob.mh.sitep2.view;

import javax.faces.event.ActionEvent;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import sv.gob.mh.sitepcommon.domain.Cliente;
import sv.gob.mh.siteputil.service.ClienteService;
import sv.gob.mh.siteputil.service.base.BaseService;
import sv.gob.mh.siteputil.view.BaseView;


@Named(value= "clienteView")
@ViewScoped
@Getter
@Setter
public class ClienteView extends BaseView<Cliente, Long> {

    @Inject
    private transient ClienteService clienteService;

    @Override
    public BaseService<Cliente, Long> getService() {
        return clienteService;
    }

    @Override
    public void prepareView(ActionEvent e) {
        super.prepareView(e);

    }
}
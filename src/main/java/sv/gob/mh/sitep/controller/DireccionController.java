package sv.gob.mh.sitep.controller;

import sv.gob.mh.sitep.Direccion;
import sv.gob.mh.sitep.facade.DireccionFacade;
import sv.gob.mh.sitep.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "direccionController")
@ViewScoped
public class DireccionController extends AbstractController<Direccion> {

    @Inject
    private ClienteController clienteIdController;
    @Inject
    private DepartamentoController departamentoIdController;
    @Inject
    private MunicipioController municipioIdController;
    @Inject
    private PaisController paisIdController;
    @Inject
    private MobilePageController mobilePageController;

    public DireccionController() {
        // Inform the Abstract parent controller of the concrete Direccion Entity
        super(Direccion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        clienteIdController.setSelected(null);
        departamentoIdController.setSelected(null);
        municipioIdController.setSelected(null);
        paisIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Cliente controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareClienteId(ActionEvent event) {
        Direccion selected = this.getSelected();
        if (selected != null && clienteIdController.getSelected() == null) {
            clienteIdController.setSelected(selected.getClienteId());
        }
    }

    /**
     * Sets the "selected" attribute of the Departamento controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDepartamentoId(ActionEvent event) {
        Direccion selected = this.getSelected();
        if (selected != null && departamentoIdController.getSelected() == null) {
            departamentoIdController.setSelected(selected.getDepartamentoId());
        }
    }

    /**
     * Sets the "selected" attribute of the Municipio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareMunicipioId(ActionEvent event) {
        Direccion selected = this.getSelected();
        if (selected != null && municipioIdController.getSelected() == null) {
            municipioIdController.setSelected(selected.getMunicipioId());
        }
    }

    /**
     * Sets the "selected" attribute of the Pais controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePaisId(ActionEvent event) {
        Direccion selected = this.getSelected();
        if (selected != null && paisIdController.getSelected() == null) {
            paisIdController.setSelected(selected.getPaisId());
        }
    }

}

package sv.gob.mh.sitep.controller;

import sv.gob.mh.sitep.Cliente;
import sv.gob.mh.sitep.Direccion;
import java.util.Collection;
import sv.gob.mh.sitep.facade.ClienteFacade;
import sv.gob.mh.sitep.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "clienteController")
@ViewScoped
public class ClienteController extends AbstractController<Cliente> {

    @Inject
    private ProfesionController profesionIdController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isDireccionCollectionEmpty;

    public ClienteController() {
        // Inform the Abstract parent controller of the concrete Cliente Entity
        super(Cliente.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        profesionIdController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDireccionCollectionEmpty();
    }

    public boolean getIsDireccionCollectionEmpty() {
        return this.isDireccionCollectionEmpty;
    }

    private void setIsDireccionCollectionEmpty() {
        Cliente selected = this.getSelected();
        if (selected != null) {
            ClienteFacade ejbFacade = (ClienteFacade) this.getFacade();
            this.isDireccionCollectionEmpty = ejbFacade.isDireccionCollectionEmpty(selected);
        } else {
            this.isDireccionCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Direccion entities that
     * are retrieved from Cliente and returns the navigation outcome.
     *
     * @return navigation outcome for Direccion page
     */
    public String navigateDireccionCollection() {
        Cliente selected = this.getSelected();
        if (selected != null) {
            ClienteFacade ejbFacade = (ClienteFacade) this.getFacade();
            Collection<Direccion> selectedDireccionCollection = ejbFacade.findDireccionCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Direccion_items", selectedDireccionCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/direccion/index";
    }

    /**
     * Sets the "selected" attribute of the Profesion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProfesionId(ActionEvent event) {
        Cliente selected = this.getSelected();
        if (selected != null && profesionIdController.getSelected() == null) {
            profesionIdController.setSelected(selected.getProfesionId());
        }
    }

}

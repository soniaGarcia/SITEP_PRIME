package sv.gob.mh.sitep.controller;

import sv.gob.mh.sitep.Municipio;
import sv.gob.mh.sitep.Direccion;
import java.util.Collection;
import sv.gob.mh.sitep.facade.MunicipioFacade;
import sv.gob.mh.sitep.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "municipioController")
@ViewScoped
public class MunicipioController extends AbstractController<Municipio> {

    @Inject
    private DepartamentoController departamentoIdController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isDireccionCollectionEmpty;

    public MunicipioController() {
        // Inform the Abstract parent controller of the concrete Municipio Entity
        super(Municipio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        departamentoIdController.setSelected(null);
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
        Municipio selected = this.getSelected();
        if (selected != null) {
            MunicipioFacade ejbFacade = (MunicipioFacade) this.getFacade();
            this.isDireccionCollectionEmpty = ejbFacade.isDireccionCollectionEmpty(selected);
        } else {
            this.isDireccionCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Direccion entities that
     * are retrieved from Municipio and returns the navigation outcome.
     *
     * @return navigation outcome for Direccion page
     */
    public String navigateDireccionCollection() {
        Municipio selected = this.getSelected();
        if (selected != null) {
            MunicipioFacade ejbFacade = (MunicipioFacade) this.getFacade();
            Collection<Direccion> selectedDireccionCollection = ejbFacade.findDireccionCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Direccion_items", selectedDireccionCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/direccion/index";
    }

    /**
     * Sets the "selected" attribute of the Departamento controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDepartamentoId(ActionEvent event) {
        Municipio selected = this.getSelected();
        if (selected != null && departamentoIdController.getSelected() == null) {
            departamentoIdController.setSelected(selected.getDepartamentoId());
        }
    }

}

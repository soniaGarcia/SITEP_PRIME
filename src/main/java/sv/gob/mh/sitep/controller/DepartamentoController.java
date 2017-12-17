package sv.gob.mh.sitep.controller;

import sv.gob.mh.sitep.Departamento;
import sv.gob.mh.sitep.Direccion;
import sv.gob.mh.sitep.Municipio;
import java.util.Collection;
import sv.gob.mh.sitep.facade.DepartamentoFacade;
import sv.gob.mh.sitep.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "departamentoController")
@ViewScoped
public class DepartamentoController extends AbstractController<Departamento> {

    @Inject
    private PaisController paisIdController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isDireccionCollectionEmpty;
    private boolean isMunicipioCollectionEmpty;

    public DepartamentoController() {
        // Inform the Abstract parent controller of the concrete Departamento Entity
        super(Departamento.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        paisIdController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDireccionCollectionEmpty();
        this.setIsMunicipioCollectionEmpty();
    }

    public boolean getIsDireccionCollectionEmpty() {
        return this.isDireccionCollectionEmpty;
    }

    private void setIsDireccionCollectionEmpty() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            this.isDireccionCollectionEmpty = ejbFacade.isDireccionCollectionEmpty(selected);
        } else {
            this.isDireccionCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Direccion entities that
     * are retrieved from Departamento and returns the navigation outcome.
     *
     * @return navigation outcome for Direccion page
     */
    public String navigateDireccionCollection() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            Collection<Direccion> selectedDireccionCollection = ejbFacade.findDireccionCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Direccion_items", selectedDireccionCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/direccion/index";
    }

    /**
     * Sets the "selected" attribute of the Pais controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePaisId(ActionEvent event) {
        Departamento selected = this.getSelected();
        if (selected != null && paisIdController.getSelected() == null) {
            paisIdController.setSelected(selected.getPaisId());
        }
    }

    public boolean getIsMunicipioCollectionEmpty() {
        return this.isMunicipioCollectionEmpty;
    }

    private void setIsMunicipioCollectionEmpty() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            this.isMunicipioCollectionEmpty = ejbFacade.isMunicipioCollectionEmpty(selected);
        } else {
            this.isMunicipioCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Municipio entities that
     * are retrieved from Departamento and returns the navigation outcome.
     *
     * @return navigation outcome for Municipio page
     */
    public String navigateMunicipioCollection() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            Collection<Municipio> selectedMunicipioCollection = ejbFacade.findMunicipioCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Municipio_items", selectedMunicipioCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/municipio/index";
    }

}

package sv.gob.mh.sitep.controller;

import sv.gob.mh.sitep.Pais;
import sv.gob.mh.sitep.Direccion;
import sv.gob.mh.sitep.Departamento;
import java.util.Collection;
import sv.gob.mh.sitep.facade.PaisFacade;
import sv.gob.mh.sitep.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "paisController")
@ViewScoped
public class PaisController extends AbstractController<Pais> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isDireccionCollectionEmpty;
    private boolean isDepartamentoCollectionEmpty;

    public PaisController() {
        // Inform the Abstract parent controller of the concrete Pais Entity
        super(Pais.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDireccionCollectionEmpty();
        this.setIsDepartamentoCollectionEmpty();
    }

    public boolean getIsDireccionCollectionEmpty() {
        return this.isDireccionCollectionEmpty;
    }

    private void setIsDireccionCollectionEmpty() {
        Pais selected = this.getSelected();
        if (selected != null) {
            PaisFacade ejbFacade = (PaisFacade) this.getFacade();
            this.isDireccionCollectionEmpty = ejbFacade.isDireccionCollectionEmpty(selected);
        } else {
            this.isDireccionCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Direccion entities that
     * are retrieved from Pais and returns the navigation outcome.
     *
     * @return navigation outcome for Direccion page
     */
    public String navigateDireccionCollection() {
        Pais selected = this.getSelected();
        if (selected != null) {
            PaisFacade ejbFacade = (PaisFacade) this.getFacade();
            Collection<Direccion> selectedDireccionCollection = ejbFacade.findDireccionCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Direccion_items", selectedDireccionCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/direccion/index";
    }

    public boolean getIsDepartamentoCollectionEmpty() {
        return this.isDepartamentoCollectionEmpty;
    }

    private void setIsDepartamentoCollectionEmpty() {
        Pais selected = this.getSelected();
        if (selected != null) {
            PaisFacade ejbFacade = (PaisFacade) this.getFacade();
            this.isDepartamentoCollectionEmpty = ejbFacade.isDepartamentoCollectionEmpty(selected);
        } else {
            this.isDepartamentoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Departamento entities
     * that are retrieved from Pais and returns the navigation outcome.
     *
     * @return navigation outcome for Departamento page
     */
    public String navigateDepartamentoCollection() {
        Pais selected = this.getSelected();
        if (selected != null) {
            PaisFacade ejbFacade = (PaisFacade) this.getFacade();
            Collection<Departamento> selectedDepartamentoCollection = ejbFacade.findDepartamentoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Departamento_items", selectedDepartamentoCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/departamento/index";
    }

}

package sv.gob.mh.sitep.controller;

import sv.gob.mh.sitep.Profesion;
import sv.gob.mh.sitep.Cliente;
import java.util.Collection;
import sv.gob.mh.sitep.facade.ProfesionFacade;
import sv.gob.mh.sitep.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "profesionController")
@ViewScoped
public class ProfesionController extends AbstractController<Profesion> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isClienteCollectionEmpty;

    public ProfesionController() {
        // Inform the Abstract parent controller of the concrete Profesion Entity
        super(Profesion.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsClienteCollectionEmpty();
    }

    public boolean getIsClienteCollectionEmpty() {
        return this.isClienteCollectionEmpty;
    }

    private void setIsClienteCollectionEmpty() {
        Profesion selected = this.getSelected();
        if (selected != null) {
            ProfesionFacade ejbFacade = (ProfesionFacade) this.getFacade();
            this.isClienteCollectionEmpty = ejbFacade.isClienteCollectionEmpty(selected);
        } else {
            this.isClienteCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Cliente entities that are
     * retrieved from Profesion and returns the navigation outcome.
     *
     * @return navigation outcome for Cliente page
     */
    public String navigateClienteCollection() {
        Profesion selected = this.getSelected();
        if (selected != null) {
            ProfesionFacade ejbFacade = (ProfesionFacade) this.getFacade();
            Collection<Cliente> selectedClienteCollection = ejbFacade.findClienteCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Cliente_items", selectedClienteCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/cliente/index";
    }

}

package tn.esprit.hrsmart.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.hrsmart.model.ChiefHumanResourcesOfficer;
import tn.esprit.hrsmart.service.AbstractFacade;

@Stateless
@Named("chiefHumanResourcesOfficer")
public class ChiefHumanResourcesOfficerFacade extends AbstractFacade<ChiefHumanResourcesOfficer> {

    @PersistenceContext(unitName = "SMARTHR")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChiefHumanResourcesOfficerFacade() {
        super(ChiefHumanResourcesOfficer.class);
    }

}

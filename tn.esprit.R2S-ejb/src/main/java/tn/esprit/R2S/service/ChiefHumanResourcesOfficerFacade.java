package tn.esprit.R2S.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.R2S.model.ChiefHumanResourcesOfficer;

@Stateless
@Named("chiefHumanResourcesOfficer")
public class ChiefHumanResourcesOfficerFacade extends AbstractFacade<ChiefHumanResourcesOfficer> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChiefHumanResourcesOfficerFacade() {
        super(ChiefHumanResourcesOfficer.class);
    }

}

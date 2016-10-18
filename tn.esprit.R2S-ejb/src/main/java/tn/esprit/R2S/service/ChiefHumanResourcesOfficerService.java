package tn.esprit.R2S.service;

import tn.esprit.R2S.model.ChiefHumanResourcesOfficer;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("chiefHumanResourcesOfficer")
public class ChiefHumanResourcesOfficerService extends AbstractService<ChiefHumanResourcesOfficer> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public ChiefHumanResourcesOfficerService() {
        super(ChiefHumanResourcesOfficer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

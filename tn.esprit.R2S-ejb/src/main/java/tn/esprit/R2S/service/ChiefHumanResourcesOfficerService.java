package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IChiefHumanResourcesOfficerService;
import tn.esprit.R2S.model.ChiefHumanResourcesOfficer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class ChiefHumanResourcesOfficerService extends AbstractService<ChiefHumanResourcesOfficer> implements IChiefHumanResourcesOfficerService {

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

package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IExperienceService;
import tn.esprit.R2S.model.Experience;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class ExperienceService extends AbstractService<Experience> implements IExperienceService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public ExperienceService() {
        super(Experience.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

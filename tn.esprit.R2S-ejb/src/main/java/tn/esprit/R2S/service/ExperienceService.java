package tn.esprit.R2S.service;

import tn.esprit.R2S.model.Experience;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("experience")
public class ExperienceService extends AbstractService<Experience> {

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

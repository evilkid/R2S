package tn.esprit.R2S.service;

import tn.esprit.R2S.model.Education;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("education")
public class EducationService extends AbstractService<Education> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public EducationService() {
        super(Education.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

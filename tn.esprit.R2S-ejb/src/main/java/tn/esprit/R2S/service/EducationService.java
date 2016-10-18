package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IEducationService;
import tn.esprit.R2S.model.Education;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class EducationService extends AbstractService<Education> implements IEducationService {

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

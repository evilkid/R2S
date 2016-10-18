package tn.esprit.R2S.service;

import tn.esprit.R2S.model.Job;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("job")
public class JobService extends AbstractService<Job> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public JobService() {
        super(Job.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

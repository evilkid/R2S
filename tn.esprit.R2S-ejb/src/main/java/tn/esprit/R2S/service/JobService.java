package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IJobService;
import tn.esprit.R2S.model.Job;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class JobService extends AbstractService<Job> implements IJobService {

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

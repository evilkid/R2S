package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.ICandidateJobService;
import tn.esprit.R2S.model.CandidateJob;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class CandidateJobService extends AbstractService<CandidateJob> implements ICandidateJobService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public CandidateJobService() {
        super(CandidateJob.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

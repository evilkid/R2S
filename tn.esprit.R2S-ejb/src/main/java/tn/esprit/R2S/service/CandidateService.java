package tn.esprit.R2S.service;

import tn.esprit.R2S.model.Candidate;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("candidate")
public class CandidateService extends AbstractService<Candidate> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public CandidateService() {
        super(Candidate.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

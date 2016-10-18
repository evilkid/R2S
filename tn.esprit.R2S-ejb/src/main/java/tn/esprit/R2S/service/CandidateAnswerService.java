package tn.esprit.R2S.service;

import tn.esprit.R2S.model.CandidateAnswer;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("candidateAnswer")
public class CandidateAnswerService extends AbstractService<CandidateAnswer> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public CandidateAnswerService() {
        super(CandidateAnswer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

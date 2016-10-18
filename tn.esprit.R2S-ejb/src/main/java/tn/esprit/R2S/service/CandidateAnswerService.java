package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.ICandidateAnswerService;
import tn.esprit.R2S.model.CandidateAnswer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Stateless
public class CandidateAnswerService extends AbstractService<CandidateAnswer> implements ICandidateAnswerService, Serializable {

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

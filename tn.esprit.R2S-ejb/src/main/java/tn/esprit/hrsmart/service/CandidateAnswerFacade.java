package tn.esprit.hrsmart.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.hrsmart.model.CandidateAnswer;
import tn.esprit.hrsmart.service.AbstractFacade;

@Stateless
@Named("candidateAnswer")
public class CandidateAnswerFacade extends AbstractFacade<CandidateAnswer> {

    @PersistenceContext(unitName = "SMARTHR")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidateAnswerFacade() {
        super(CandidateAnswer.class);
    }

}

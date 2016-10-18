package tn.esprit.R2S.service;

import tn.esprit.R2S.model.CandidateQuizModel;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("candidateQuizModel")
public class CandidateQuizModelService extends AbstractService<CandidateQuizModel> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public CandidateQuizModelService() {
        super(CandidateQuizModel.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

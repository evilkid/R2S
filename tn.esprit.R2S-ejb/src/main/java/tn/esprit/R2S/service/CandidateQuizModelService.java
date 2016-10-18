package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.ICandidateQuizModelService;
import tn.esprit.R2S.model.CandidateQuizModel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class CandidateQuizModelService extends AbstractService<CandidateQuizModel> implements ICandidateQuizModelService {

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

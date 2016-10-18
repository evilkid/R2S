package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IQuizModelService;
import tn.esprit.R2S.model.QuizModel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class QuizModelService extends AbstractService<QuizModel> implements IQuizModelService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public QuizModelService() {
        super(QuizModel.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

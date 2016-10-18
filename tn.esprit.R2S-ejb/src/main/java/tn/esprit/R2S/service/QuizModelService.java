package tn.esprit.R2S.service;

import tn.esprit.R2S.model.QuizModel;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("quizModel")
public class QuizModelService extends AbstractService<QuizModel> {

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

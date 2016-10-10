package tn.esprit.R2S.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.R2S.model.QuizModel;

@Stateless
@Named("quizModel")
public class QuizModelFacade extends AbstractFacade<QuizModel> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuizModelFacade() {
        super(QuizModel.class);
    }

}

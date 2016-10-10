package tn.esprit.hrsmart.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.hrsmart.model.QuizModel;
import tn.esprit.hrsmart.service.AbstractFacade;

@Stateless
@Named("quizModel")
public class QuizModelFacade extends AbstractFacade<QuizModel> {

    @PersistenceContext(unitName = "SMARTHR")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuizModelFacade() {
        super(QuizModel.class);
    }

}

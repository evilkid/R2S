package tn.esprit.R2S.service;

import tn.esprit.R2S.model.Question;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("question")
public class QuestionService extends AbstractService<Question> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public QuestionService() {
        super(Question.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IQuestionService;
import tn.esprit.R2S.model.Question;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class QuestionService extends AbstractService<Question> implements IQuestionService {

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

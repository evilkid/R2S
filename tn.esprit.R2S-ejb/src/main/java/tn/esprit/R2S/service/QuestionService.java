package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IQuestionService;
import tn.esprit.R2S.model.Question;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

    @Override
    public List<Question> findRandomQuestions(int numberOfQuestions) {
        String query = "SELECT q FROM Question q ORDER BY RAND()";
        Query q = em.createQuery(query);
        q.setMaxResults(numberOfQuestions);
        return q.getResultList();
    }
}

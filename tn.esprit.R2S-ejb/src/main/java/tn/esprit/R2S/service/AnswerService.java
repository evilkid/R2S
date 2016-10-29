package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IAnswerService;
import tn.esprit.R2S.model.Answer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless

public class AnswerService extends AbstractService<Answer> implements IAnswerService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public AnswerService() {
        super(Answer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Answer> findCorrectAnswers(Long questionId) {
        String query = "SELECT a FROM Answer a WHERE a.question.id = :id AND a.correct = true ORDER BY RAND()";
        Query q = em.createQuery(query);
        q.setParameter("id", questionId);
        List<Answer> answers = q.getResultList();
        return answers;
    }

    @Override
    public List<Answer> findWrongAnswers(Long questionId) {
        String query = "SELECT a FROM Answer a WHERE a.question.id = :id AND a.correct = false ORDER BY RAND()";
        Query q = em.createQuery(query);
        q.setParameter("id", questionId);
        List<Answer> answers = q.getResultList();
        return answers;
    }
}

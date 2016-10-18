package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IAnswerService;
import tn.esprit.R2S.model.Answer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}

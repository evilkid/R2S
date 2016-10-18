package tn.esprit.R2S.service;

import tn.esprit.R2S.model.Answer;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("answer")
public class AnswerService extends AbstractService<Answer> {

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

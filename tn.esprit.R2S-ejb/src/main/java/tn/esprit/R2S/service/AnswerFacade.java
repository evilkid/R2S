package tn.esprit.R2S.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.R2S.interfaces.IAnswerFacade;
import tn.esprit.R2S.model.Answer;

@Stateless
@Named("answer")
public class AnswerFacade extends AbstractFacade<Answer> implements IAnswerFacade{

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerFacade() {
        super(Answer.class);
    }

}

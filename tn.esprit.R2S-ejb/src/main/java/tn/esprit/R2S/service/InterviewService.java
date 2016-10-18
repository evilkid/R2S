package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IInterviewService;
import tn.esprit.R2S.model.Interview;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class InterviewService extends AbstractService<Interview> implements IInterviewService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public InterviewService() {
        super(Interview.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

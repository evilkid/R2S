package tn.esprit.hrsmart.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.hrsmart.model.Interview;
import tn.esprit.hrsmart.service.AbstractFacade;

@Stateless
@Named("interview")
public class InterviewFacade extends AbstractFacade<Interview> {

    @PersistenceContext(unitName = "SMARTHR")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InterviewFacade() {
        super(Interview.class);
    }

}

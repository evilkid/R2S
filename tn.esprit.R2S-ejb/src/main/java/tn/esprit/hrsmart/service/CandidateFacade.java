package tn.esprit.hrsmart.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.hrsmart.model.Candidate;
import tn.esprit.hrsmart.service.AbstractFacade;

@Stateless
@Named("candidate")
public class CandidateFacade extends AbstractFacade<Candidate> {

    @PersistenceContext(unitName = "SMARTHR")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidateFacade() {
        super(Candidate.class);
    }

}

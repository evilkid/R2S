package tn.esprit.hrsmart.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.hrsmart.model.CandidateJob;
import tn.esprit.hrsmart.service.AbstractFacade;

@Stateless
@Named("candidateJob")
public class CandidateJobFacade extends AbstractFacade<CandidateJob> {

    @PersistenceContext(unitName = "SMARTHR")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidateJobFacade() {
        super(CandidateJob.class);
    }

}

package tn.esprit.hrsmart.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.hrsmart.model.Experience;
import tn.esprit.hrsmart.service.AbstractFacade;

@Stateless
@Named("experience")
public class ExperienceFacade extends AbstractFacade<Experience> {

    @PersistenceContext(unitName = "SMARTHR")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExperienceFacade() {
        super(Experience.class);
    }

}

package tn.esprit.hrsmart.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.hrsmart.model.Certification;
import tn.esprit.hrsmart.service.AbstractFacade;

@Stateless
@Named("certification")
public class CertificationFacade extends AbstractFacade<Certification> {

    @PersistenceContext(unitName = "SMARTHR")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CertificationFacade() {
        super(Certification.class);
    }

}

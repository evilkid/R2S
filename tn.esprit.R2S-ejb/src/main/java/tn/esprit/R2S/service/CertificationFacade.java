package tn.esprit.R2S.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.R2S.model.Certification;

@Stateless
@Named("certification")
public class CertificationFacade extends AbstractFacade<Certification> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CertificationFacade() {
        super(Certification.class);
    }

}

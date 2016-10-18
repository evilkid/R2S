package tn.esprit.R2S.service;

import tn.esprit.R2S.model.Certification;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("certification")
public class CertificationService extends AbstractService<Certification> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public CertificationService() {
        super(Certification.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

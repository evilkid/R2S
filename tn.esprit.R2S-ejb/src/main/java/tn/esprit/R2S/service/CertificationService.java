package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.ICertificationService;
import tn.esprit.R2S.model.Certification;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class CertificationService extends AbstractService<Certification> implements ICertificationService {

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

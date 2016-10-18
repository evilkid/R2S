package tn.esprit.R2S.service;

import tn.esprit.R2S.model.EmailModel;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("emailModel")
public class EmailModelService extends AbstractService<EmailModel> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public EmailModelService() {
        super(EmailModel.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

package tn.esprit.hrsmart.service;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.hrsmart.model.EmailModel;
import tn.esprit.hrsmart.service.AbstractFacade;

@Stateless
@Named("emailModel")
public class EmailModelFacade extends AbstractFacade<EmailModel> {

    @PersistenceContext(unitName = "SMARTHR")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmailModelFacade() {
        super(EmailModel.class);
    }

}

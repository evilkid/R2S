package tn.esprit.R2S.service;

import tn.esprit.R2S.model.Notification;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("notification")
public class NotificationService extends AbstractService<Notification> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public NotificationService() {
        super(Notification.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

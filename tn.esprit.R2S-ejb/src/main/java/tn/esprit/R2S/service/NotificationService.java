package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.INotificationService;
import tn.esprit.R2S.model.Notification;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class NotificationService extends AbstractService<Notification> implements INotificationService {

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

package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Notification;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface INotificationService {
    void create(Notification entity);

    Notification edit(Notification entity);

    void remove(Notification entity);

    Notification find(Object id);

    List<Notification> findAll();
}

package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Notification;

import java.util.List;

/**
 * Created by EvilKids on 10/18/2016.
 */
public interface INotificationService {
    void create(Notification notification);

    Notification edit(Notification notification);

    void remove(Notification notification);

    Notification find(Object id);

    List<Notification> findAll();
}

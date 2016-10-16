package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.Notification;
import tn.esprit.R2S.service.NotificationFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/notification")
public class NotificationController {

    @Inject
    private NotificationFacade notificationFacade;

    @POST
    public Response createNotification(Notification notification) throws URISyntaxException {
        notificationFacade.create(notification);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/notification/" + notification.getId())),
                "notification", notification.getId().toString())
                .entity(notification).build();
    }

    @PUT
    public Response updateNotification(Notification notification) throws URISyntaxException {
        notificationFacade.edit(notification);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "notification", notification.getId().toString())
                .entity(notification).build();
    }

    @GET
    public List<Notification> getAllNotifications() {
        List<Notification> notifications = notificationFacade.findAll();
        return notifications;
    }

    @Path("/{id}")
    @GET
    public Response getNotification(@PathParam("id") Long id) {
        Notification notification = notificationFacade.find(id);
        return Optional.ofNullable(notification)
                .map(result -> Response.status(Response.Status.OK).entity(notification).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeNotification(@PathParam("id") Long id) {
        notificationFacade.remove(notificationFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "notification", id.toString()).build();
    }

}

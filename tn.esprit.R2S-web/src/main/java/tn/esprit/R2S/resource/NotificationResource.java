package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.INotificationService;
import tn.esprit.R2S.model.Notification;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/notification")
public class NotificationResource {


    @EJB
    private INotificationService notificationService;

    @POST
    public Response createNotification(Notification notification) throws URISyntaxException {

        notificationService.create(notification);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/notification/" + notification.getId())),
                "notification", notification.getId().toString())
                .entity(notification).build();
    }
    @PUT
    public Response updateNotification(Notification notification) throws URISyntaxException {

        notificationService.edit(notification);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "notification", notification.getId().toString())
                .entity(notification).build();
    }
    @GET
    public List<Notification> getAllNotifications() {

        List<Notification> notifications = notificationService.findAll();
        return notifications;
    }

    @Path("/{id}")
    @GET
    public Response getNotification(@PathParam("id") Long id) {

        Notification notification = notificationService.find(id);
        return Optional.ofNullable(notification)
                .map(result -> Response.status(Response.Status.OK).entity(notification).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeNotification(@PathParam("id") Long id) {

        notificationService.remove(notificationService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "notification", id.toString()).build();
    }

}

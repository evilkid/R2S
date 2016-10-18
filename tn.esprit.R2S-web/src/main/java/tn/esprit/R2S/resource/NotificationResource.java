package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.Notification;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.NotificationService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Notification.
 */
@Path("/api/notification")

public class NotificationResource {


    @EJB
    private NotificationService notificationService;

    /**
     * POST : Create a new notification.
     *
     * @param notification the notification to create
     * @return the Response with status 201 (Created) and with body the new
     * notification, or with status 400 (Bad Request) if the notification has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createNotification(Notification notification) throws URISyntaxException {

        notificationService.create(notification);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/notification/" + notification.getId())),
                "notification", notification.getId().toString())
                .entity(notification).build();
    }

    /**
     * PUT : Updates an existing notification.
     *
     * @param notification the notification to update
     * @return the Response with status 200 (OK) and with body the updated
     * notification, or with status 400 (Bad Request) if the notification is not
     * valid, or with status 500 (Internal Server Error) if the notification
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateNotification(Notification notification) throws URISyntaxException {

        notificationService.edit(notification);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "notification", notification.getId().toString())
                .entity(notification).build();
    }

    /**
     * GET : get all the notifications. <% if (pagination != 'no') {} @param
     * pageable the p
     * <p>
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     *
     * @return the Response with status 200 (OK) and the list of notifications
     * in body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Notification> getAllNotifications() {

        List<Notification> notifications = notificationService.findAll();
        return notifications;
    }

    /**
     * GET /:id : get the "id" notification.
     *
     * @param id the id of the notification to retrieve
     * @return the Response with status 200 (OK) and with body the notification,
     * or with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getNotification(@PathParam("id") Long id) {

        Notification notification = notificationService.find(id);
        return Optional.ofNullable(notification)
                .map(result -> Response.status(Response.Status.OK).entity(notification).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" notification.
     *
     * @param id the id of the notification to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeNotification(@PathParam("id") Long id) {

        notificationService.remove(notificationService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "notification", id.toString()).build();
    }

}

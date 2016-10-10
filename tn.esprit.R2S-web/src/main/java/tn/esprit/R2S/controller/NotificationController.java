package tn.esprit.R2S.controller;

import tn.esprit.R2S.model.Notification;
import tn.esprit.R2S.service.NotificationFacade;
import tn.esprit.R2S.controller.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST controller for managing Notification.
 */
@Path("/api/notification")
public class NotificationController {

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Inject
    private NotificationFacade notificationFacade;

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
        log.debug("REST request to save Notification : {}", notification);
        notificationFacade.create(notification);
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
        log.debug("REST request to update Notification : {}", notification);
        notificationFacade.edit(notification);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "notification", notification.getId().toString())
                .entity(notification).build();
    }

    /**
     * GET : get all the notifications. <% if (pagination != 'no') {} @param
     * pageable the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of notifications
     * in body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Notification> getAllNotifications() {
        log.debug("REST request to get all Notifications");
        List<Notification> notifications = notificationFacade.findAll();
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
        log.debug("REST request to get Notification : {}", id);
        Notification notification = notificationFacade.find(id);
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
        log.debug("REST request to delete Notification : {}", id);
        notificationFacade.remove(notificationFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "notification", id.toString()).build();
    }

}

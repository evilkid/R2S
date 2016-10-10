package tn.esprit.hrsmart.controller;

import tn.esprit.hrsmart.model.EmailModel;
import tn.esprit.hrsmart.service.EmailModelFacade;
import tn.esprit.hrsmart.controller.util.HeaderUtil;
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
 * REST controller for managing EmailModel.
 */
@Path("/api/email-model")
public class EmailModelController {

    private final Logger log = LoggerFactory.getLogger(EmailModelController.class);

    @Inject
    private EmailModelFacade emailModelFacade;

    /**
     * POST : Create a new emailModel.
     *
     * @param emailModel the emailModel to create
     * @return the Response with status 201 (Created) and with body the new
     * emailModel, or with status 400 (Bad Request) if the emailModel has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createEmailModel(EmailModel emailModel) throws URISyntaxException {
        log.debug("REST request to save EmailModel : {}", emailModel);
        emailModelFacade.create(emailModel);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/email-model/" + emailModel.getId())),
                "emailModel", emailModel.getId().toString())
                .entity(emailModel).build();
    }

    /**
     * PUT : Updates an existing emailModel.
     *
     * @param emailModel the emailModel to update
     * @return the Response with status 200 (OK) and with body the updated
     * emailModel, or with status 400 (Bad Request) if the emailModel is not
     * valid, or with status 500 (Internal Server Error) if the emailModel
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateEmailModel(EmailModel emailModel) throws URISyntaxException {
        log.debug("REST request to update EmailModel : {}", emailModel);
        emailModelFacade.edit(emailModel);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "emailModel", emailModel.getId().toString())
                .entity(emailModel).build();
    }

    /**
     * GET : get all the emailModels. <% if (pagination != 'no') {} @param
     * pageable the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of emailModels in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<EmailModel> getAllEmailModels() {
        log.debug("REST request to get all EmailModels");
        List<EmailModel> emailModels = emailModelFacade.findAll();
        return emailModels;
    }

    /**
     * GET /:id : get the "id" emailModel.
     *
     * @param id the id of the emailModel to retrieve
     * @return the Response with status 200 (OK) and with body the emailModel,
     * or with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getEmailModel(@PathParam("id") Long id) {
        log.debug("REST request to get EmailModel : {}", id);
        EmailModel emailModel = emailModelFacade.find(id);
        return Optional.ofNullable(emailModel)
                .map(result -> Response.status(Response.Status.OK).entity(emailModel).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" emailModel.
     *
     * @param id the id of the emailModel to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeEmailModel(@PathParam("id") Long id) {
        log.debug("REST request to delete EmailModel : {}", id);
        emailModelFacade.remove(emailModelFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "emailModel", id.toString()).build();
    }

}

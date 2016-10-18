package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.EmailModel;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.EmailModelService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing EmailModel.
 */
@Path("/api/email-model")

public class EmailModelResource {


    @EJB
    private EmailModelService emailModelService;

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

        emailModelService.create(emailModel);
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

        emailModelService.edit(emailModel);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "emailModel", emailModel.getId().toString())
                .entity(emailModel).build();
    }

    /**
     * GET : get all the emailModels. <% if (pagination != 'no') {} @param
     * pageable the p
     * <p>
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     *
     * @return the Response with status 200 (OK) and the list of emailModels in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<EmailModel> getAllEmailModels() {

        List<EmailModel> emailModels = emailModelService.findAll();
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

        EmailModel emailModel = emailModelService.find(id);
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

        emailModelService.remove(emailModelService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "emailModel", id.toString()).build();
    }

}

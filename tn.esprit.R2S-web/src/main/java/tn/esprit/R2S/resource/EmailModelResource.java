package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IEmailModelService;
import tn.esprit.R2S.model.EmailModel;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/email-model")
public class EmailModelResource {


    @EJB
    private IEmailModelService emailModelService;

    @POST
    public Response createEmailModel(EmailModel emailModel) throws URISyntaxException {

        emailModelService.create(emailModel);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/email-model/" + emailModel.getId())),
                "emailModel", emailModel.getId().toString())
                .entity(emailModel).build();
    }

    @PUT
    public Response updateEmailModel(EmailModel emailModel) throws URISyntaxException {

        emailModelService.edit(emailModel);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "emailModel", emailModel.getId().toString())
                .entity(emailModel).build();
    }
    @GET
    public List<EmailModel> getAllEmailModels() {

        List<EmailModel> emailModels = emailModelService.findAll();
        return emailModels;
    }

    @Path("/{id}")
    @GET
    public Response getEmailModel(@PathParam("id") Long id) {

        EmailModel emailModel = emailModelService.find(id);
        return Optional.ofNullable(emailModel)
                .map(result -> Response.status(Response.Status.OK).entity(emailModel).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeEmailModel(@PathParam("id") Long id) {

        emailModelService.remove(emailModelService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "emailModel", id.toString()).build();
    }

}

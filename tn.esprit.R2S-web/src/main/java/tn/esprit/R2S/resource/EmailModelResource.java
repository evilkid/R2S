package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IEmailModelService;
import tn.esprit.R2S.model.EmailModel;
import tn.esprit.R2S.resource.util.Roles;
import tn.esprit.R2S.resource.util.Secured;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/email-model")
@Secured(Roles.RECRUITMENT_MANAGER)
public class EmailModelResource {


    @EJB
    private IEmailModelService emailModelService;

    @POST
    public Response createEmailModel(EmailModel emailModel) throws URISyntaxException {

        emailModelService.create(emailModel);
        return Response.created(new URI("/resources/api/email-model/" + emailModel.getId())).entity(emailModel).build();
    }

    @PUT
    public Response updateEmailModel(EmailModel emailModel) throws URISyntaxException {

        emailModelService.edit(emailModel);
        return Response.ok().entity(emailModel).build();
    }

    @GET
    public List<EmailModel> getAllEmailModels() {

        return emailModelService.findAll();
    }

    @Path("/{id}")
    @GET
    public Response getEmailModel(@PathParam("id") Long id) {

        return Optional.ofNullable(emailModelService.find(id))
                .map(emailModel -> Response.ok(emailModel).build())
                .orElseThrow(NotFoundException::new);
    }

    @Path("/{id}")
    @DELETE
    public Response removeEmailModel(@PathParam("id") Long id) {

        return Optional.ofNullable(emailModelService.find(id))
                .map(emailModel -> {
                    emailModelService.remove(emailModel);
                    return Response.ok().build();
                }).orElseThrow(NotFoundException::new);
    }

}

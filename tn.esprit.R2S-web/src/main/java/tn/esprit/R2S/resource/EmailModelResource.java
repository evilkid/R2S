package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.*;
import tn.esprit.R2S.model.EmailModel;

import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/email-model")
//@Secured(Roles.RECRUITMENT_MANAGER)
public class EmailModelResource {


    @EJB
    private IEmailModelService emailModelService;

    @EJB
    private ICandidateService candidateService;

    @EJB
    private IJobService jobService;

    @EJB
    private IJobFieldService jobFieldService;

    @EJB
    private ICandidateFieldService candidateFieldService;


    @GET
    @Path("/send/{email-model-id}/{candidate-cin}/{job-id}")
    public Response sendEmail(@PathParam("email-model-id") Long emailModelId,
                              @PathParam("candidate-cin") Long cin,
                              @PathParam("job-id") Long jobId) throws JMSException {
        emailModelService.sendEmail(emailModelId, cin, jobId);

        return Response.ok().build();
    }

    @GET
    @Path("/parse/{email-model-id}/{candidate-cin}/{job-id}")
    public Response parseEmail(@PathParam("email-model-id") Long emailModelId,
                               @PathParam("candidate-cin") Long cin,
                               @PathParam("job-id") Long jobId) throws JMSException {
        return Response.ok(emailModelService.parseEmail(emailModelId, cin, jobId)).build();
    }

    @POST
    public Response createEmailModel(EmailModel emailModel) throws URISyntaxException {

        emailModelService.create(emailModel);
        return Response.created(new URI("/resources/api/email-model/" + emailModel.getId())).entity(emailModel).build();
    }

    @PUT
    @Path("{email-model-id}")
    public Response updateEmailModel(EmailModel emailModel, @PathParam("email-model-id") Long emailModelId) throws URISyntaxException {

        if (!emailModel.getId().equals(emailModelId)) {
            throw new NotAllowedException("The ids does not match");
        }

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

    @Path("/variables")
    @GET
    public Response getVariables() {
        String variables = emailModelService.getVariables();
        return Response.ok(variables).build();
    }

}

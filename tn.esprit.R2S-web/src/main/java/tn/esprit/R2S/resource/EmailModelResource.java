package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IEmailModelService;
import tn.esprit.R2S.model.Address;
import tn.esprit.R2S.model.EmailModel;
import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.model.Users;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/email-model")
//@Secured(Roles.RECRUITMENT_MANAGER)
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

    @Path("/variables")
    @GET
    public Response getVariables() {

        //TODO: investigate more
        JsonObjectBuilder variables = Json.createObjectBuilder();

        Field[] user = Users.class.getDeclaredFields();
        Field[] address = Address.class.getDeclaredFields();
        Field[] job = Job.class.getDeclaredFields();

        //candidate fields
        JsonObjectBuilder candidateObjectBuilder = Json.createObjectBuilder();
        for (Field field : user) {
            if (!field.getName().contains("address")) {
                candidateObjectBuilder.add(field.getName(), "{{candidate." + field.getName() + "}}");
            }
        }

        JsonObjectBuilder addressObjectBuilder = Json.createObjectBuilder();
        for (Field field : address) {
            addressObjectBuilder.add(field.getName(), "{{candidate.address." + field.getName() + "}}");
        }

        candidateObjectBuilder.add("Address", addressObjectBuilder.build());

        variables.add("Candidate", candidateObjectBuilder.build());

        //referee fields
        JsonObjectBuilder refereeObjectBuilder = Json.createObjectBuilder();
        for (Field field : user) {
            if (!field.getName().contains("address")) {
                refereeObjectBuilder.add(field.getName(), "{{referee." + field.getName() + "}}");
            }
        }

        variables.add("Referee", refereeObjectBuilder.build());

        //job fields
        JsonObjectBuilder jobObjectBuilder = Json.createObjectBuilder();
        for (Field field : job) {
            System.out.println(field + ", " + field.getType().getPackage().getName());
            if (field.getType() != List.class) {
                jobObjectBuilder.add(field.getName(), "{{job." + field.getName() + "}}");
            }
        }

        variables.add("Job", jobObjectBuilder.build());
        return Response.ok(variables.build()).build();
    }
}

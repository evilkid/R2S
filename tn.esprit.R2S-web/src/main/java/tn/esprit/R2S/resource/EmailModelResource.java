package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IEmailModelService;
import tn.esprit.R2S.model.Candidate;
import tn.esprit.R2S.model.EmailModel;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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


        //if (Candidate.class.getSuperclass() != null) {

        JsonArrayBuilder inner = Json.createArrayBuilder();

        Field[] fields = Candidate.class.getDeclaredFields();
        if (Candidate.class.getSuperclass() != null) {
            Field[] superFields = Candidate.class.getSuperclass().getDeclaredFields();

            fields = Stream.concat(Arrays.stream(fields), Arrays.stream(superFields))
                    .toArray(Field[]::new);
        }


        System.out.println(Arrays.toString(fields));


        for (Field field : fields) {

            if (field.getType().getPackage().getName().startsWith("tn.esprit.R2S.model")) {
                JsonArrayBuilder innerArray = Json.createArrayBuilder();
                for (Field innerField : field.getType().getDeclaredFields()) {
                    innerArray.add(innerField.getName());
                }

                inner.add(
                        Json.createObjectBuilder()
                                .add(field.getName(), innerArray)
                );

            } else {
                inner.add(field.getName());
            }


            //System.out.println(field.getType());
        }
        variables.add("candidate", inner);
        //}

/*
        JsonArrayBuilder inner = Json.createArrayBuilder();
        for (Field field : Candidate.class.getDeclaredFields()) {

            inner.add(field.getName());
        }

        variables.add("candidate", inner);*/
        return Response.ok(variables.build()).build();
    }
}

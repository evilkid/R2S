package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.Education;
import tn.esprit.R2S.service.EducationFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@Path("/api/education")
public class EducationController {


    @Inject
    private EducationFacade educationFacade;

    @POST
    public Response createEducation(Education education) throws URISyntaxException {
        educationFacade.create(education);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/education/" + education.getId())),
                "education", education.getId().toString())
                .entity(education).build();
    }

    @PUT
    public Response updateEducation(Education education) throws URISyntaxException {
        educationFacade.edit(education);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "education", education.getId().toString())
                .entity(education).build();
    }

    @GET
    public List<Education> getAllEducations() {
        List<Education> educations = educationFacade.findAll();
        return educations;
    }

    @Path("/{id}")
    @GET
    public Response getEducation(@PathParam("id") Long id) {
        Education education = educationFacade.find(id);
        return Optional.ofNullable(education)
                .map(result -> Response.status(Response.Status.OK).entity(education).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeEducation(@PathParam("id") Long id) {
        educationFacade.remove(educationFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "education", id.toString()).build();
    }

}

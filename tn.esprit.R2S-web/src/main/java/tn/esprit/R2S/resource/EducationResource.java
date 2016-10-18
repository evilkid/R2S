package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IEducationService;
import tn.esprit.R2S.model.Education;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@Path("/api/education")
public class EducationResource {


    @EJB
    private IEducationService educationService;

    @POST
    public Response createEducation(Education education) throws URISyntaxException {

        educationService.create(education);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/education/" + education.getId())),
                "education", education.getId().toString())
                .entity(education).build();
    }

    @PUT
    public Response updateEducation(Education education) throws URISyntaxException {

        educationService.edit(education);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "education", education.getId().toString())
                .entity(education).build();
    }

    @GET
    public List<Education> getAllEducations() {

        List<Education> educations = educationService.findAll();
        return educations;
    }

    @Path("/{id}")
    @GET
    public Response getEducation(@PathParam("id") Long id) {

        Education education = educationService.find(id);
        return Optional.ofNullable(education)
                .map(result -> Response.status(Response.Status.OK).entity(education).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    @Path("/{id}")
    @DELETE
    public Response removeEducation(@PathParam("id") Long id) {

        educationService.remove(educationService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "education", id.toString()).build();
    }

}

package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IExperienceService;
import tn.esprit.R2S.model.Experience;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/experience")
public class ExperienceResource {

    @EJB
    private IExperienceService ExperienceService;

    @POST
    public Response createExperience(Experience Experience) throws URISyntaxException {

        ExperienceService.create(Experience);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/Experience/" + Experience.getId())),
                "Experience", Experience.getId().toString())
                .entity(Experience).build();
    }

    @PUT
    public Response updateExperience(Experience Experience) throws URISyntaxException {

        ExperienceService.edit(Experience);
        return Response.ok(Experience).build();
    }

    @GET
    public List<Experience> getAllExperiences() {

        List<Experience> Experiences = ExperienceService.findAll();
        return Experiences;
    }

    @Path("/{id}")
    @GET

    public Response getExperience(@PathParam("id") Long id) {

        Experience Experience = ExperienceService.find(id);
        return Optional.ofNullable(Experience)
                .map(result -> Response.status(Response.Status.OK).entity(Experience).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeExperience(@PathParam("id") Long id) {

        ExperienceService.remove(ExperienceService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "Experience", id.toString()).build();
    }

}

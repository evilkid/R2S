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
    private IExperienceService experienceService;

    @POST
    public Response createExperience(Experience experience) throws URISyntaxException {

        experienceService.create(experience);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/experience/" + experience.getId())),
                "experience", experience.getId().toString())
                .entity(experience).build();
    }

    @PUT
    public Response updateExperience(Experience experience) throws URISyntaxException {

        experienceService.edit(experience);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "experience", experience.getId().toString())
                .entity(experience).build();
    }
    @GET
    public List<Experience> getAllExperiences() {

        List<Experience> experiences = experienceService.findAll();
        return experiences;
    }
    @Path("/{id}")
    @GET
    public Response getExperience(@PathParam("id") Long id) {

        Experience experience = experienceService.find(id);
        return Optional.ofNullable(experience)
                .map(result -> Response.status(Response.Status.OK).entity(experience).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeExperience(@PathParam("id") Long id) {

        experienceService.remove(experienceService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "experience", id.toString()).build();
    }

}

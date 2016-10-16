package tn.esprit.R2S.controller;

import tn.esprit.R2S.model.Experience;
import tn.esprit.R2S.service.ExperienceFacade;
import tn.esprit.R2S.controller.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/api/experience")
public class ExperienceController {

    @Inject
    private ExperienceFacade experienceFacade;

    @POST
    public Response createExperience(Experience experience) throws URISyntaxException {
        experienceFacade.create(experience);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/experience/" + experience.getId())),
                "experience", experience.getId().toString())
                .entity(experience).build();
    }

    @PUT
    public Response updateExperience(Experience experience) throws URISyntaxException {
        experienceFacade.edit(experience);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "experience", experience.getId().toString())
                .entity(experience).build();
    }

    @GET
    public List<Experience> getAllExperiences() {
        List<Experience> experiences = experienceFacade.findAll();
        return experiences;
    }

    @Path("/{id}")
    @GET
    public Response getExperience(@PathParam("id") Long id) {
        Experience experience = experienceFacade.find(id);
        return Optional.ofNullable(experience)
                .map(result -> Response.status(Response.Status.OK).entity(experience).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeExperience(@PathParam("id") Long id) {
        experienceFacade.remove(experienceFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "experience", id.toString()).build();
    }

}

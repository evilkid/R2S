package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.Experience;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.ExperienceService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Experience.
 */
@Path("/api/experience")

public class ExperienceResource {


    @EJB
    private ExperienceService experienceService;

    /**
     * POST : Create a new experience.
     *
     * @param experience the experience to create
     * @return the Response with status 201 (Created) and with body the new
     * experience, or with status 400 (Bad Request) if the experience has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createExperience(Experience experience) throws URISyntaxException {

        experienceService.create(experience);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/experience/" + experience.getId())),
                "experience", experience.getId().toString())
                .entity(experience).build();
    }

    /**
     * PUT : Updates an existing experience.
     *
     * @param experience the experience to update
     * @return the Response with status 200 (OK) and with body the updated
     * experience, or with status 400 (Bad Request) if the experience is not
     * valid, or with status 500 (Internal Server Error) if the experience
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateExperience(Experience experience) throws URISyntaxException {

        experienceService.edit(experience);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "experience", experience.getId().toString())
                .entity(experience).build();
    }

    /**
     * GET : get all the experiences. <% if (pagination != 'no') {} @param
     * pageable the p
     * <p>
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     *
     * @return the Response with status 200 (OK) and the list of experiences in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Experience> getAllExperiences() {

        List<Experience> experiences = experienceService.findAll();
        return experiences;
    }

    /**
     * GET /:id : get the "id" experience.
     *
     * @param id the id of the experience to retrieve
     * @return the Response with status 200 (OK) and with body the experience,
     * or with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getExperience(@PathParam("id") Long id) {

        Experience experience = experienceService.find(id);
        return Optional.ofNullable(experience)
                .map(result -> Response.status(Response.Status.OK).entity(experience).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" experience.
     *
     * @param id the id of the experience to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeExperience(@PathParam("id") Long id) {

        experienceService.remove(experienceService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "experience", id.toString()).build();
    }

}

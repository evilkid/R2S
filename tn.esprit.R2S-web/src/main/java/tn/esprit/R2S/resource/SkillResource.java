package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.Skill;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.SkillService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Skill.
 */
@Path("/api/skill")

public class SkillResource {


    @EJB
    private SkillService skillService;

    /**
     * POST : Create a new skill.
     *
     * @param skill the skill to create
     * @return the Response with status 201 (Created) and with body the new
     * skill, or with status 400 (Bad Request) if the skill has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createSkill(Skill skill) throws URISyntaxException {

        skillService.create(skill);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/skill/" + skill.getId())),
                "skill", skill.getId().toString())
                .entity(skill).build();
    }

    /**
     * PUT : Updates an existing skill.
     *
     * @param skill the skill to update
     * @return the Response with status 200 (OK) and with body the updated
     * skill, or with status 400 (Bad Request) if the skill is not valid, or
     * with status 500 (Internal Server Error) if the skill couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateSkill(Skill skill) throws URISyntaxException {

        skillService.edit(skill);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "skill", skill.getId().toString())
                .entity(skill).build();
    }

    /**
     * GET : get all the skills. <% if (pagination != 'no') {} @param pageable
     * the p
     * <p>
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     *
     * @return the Response with status 200 (OK) and the list of skills in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Skill> getAllSkills() {

        List<Skill> skills = skillService.findAll();
        return skills;
    }

    /**
     * GET /:id : get the "id" skill.
     *
     * @param id the id of the skill to retrieve
     * @return the Response with status 200 (OK) and with body the skill, or
     * with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getSkill(@PathParam("id") Long id) {

        Skill skill = skillService.find(id);
        return Optional.ofNullable(skill)
                .map(result -> Response.status(Response.Status.OK).entity(skill).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" skill.
     *
     * @param id the id of the skill to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeSkill(@PathParam("id") Long id) {

        skillService.remove(skillService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "skill", id.toString()).build();
    }

}

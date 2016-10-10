package tn.esprit.R2S.controller;

import tn.esprit.R2S.model.Skill;
import tn.esprit.R2S.service.SkillFacade;
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

/**
 * REST controller for managing Skill.
 */
@Path("/api/skill")
public class SkillController {

    private final Logger log = LoggerFactory.getLogger(SkillController.class);

    @Inject
    private SkillFacade skillFacade;

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
        log.debug("REST request to save Skill : {}", skill);
        skillFacade.create(skill);
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
        log.debug("REST request to update Skill : {}", skill);
        skillFacade.edit(skill);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "skill", skill.getId().toString())
                .entity(skill).build();
    }

    /**
     * GET : get all the skills. <% if (pagination != 'no') {} @param pageable
     * the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of skills in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Skill> getAllSkills() {
        log.debug("REST request to get all Skills");
        List<Skill> skills = skillFacade.findAll();
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
        log.debug("REST request to get Skill : {}", id);
        Skill skill = skillFacade.find(id);
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
        log.debug("REST request to delete Skill : {}", id);
        skillFacade.remove(skillFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "skill", id.toString()).build();
    }

}

package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.Skill;
import tn.esprit.R2S.service.SkillFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/skill")
public class SkillController {

    @Inject
    private SkillFacade skillFacade;

    @POST
    public Response createSkill(Skill skill) throws URISyntaxException {
        skillFacade.create(skill);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/skill/" + skill.getId())),
                "skill", skill.getId().toString())
                .entity(skill).build();
    }

    @PUT
    public Response updateSkill(Skill skill) throws URISyntaxException {
        skillFacade.edit(skill);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "skill", skill.getId().toString())
                .entity(skill).build();
    }

    @GET
    public List<Skill> getAllSkills() {
        List<Skill> skills = skillFacade.findAll();
        return skills;
    }

    @Path("/{id}")
    @GET
    public Response getSkill(@PathParam("id") Long id) {
        Skill skill = skillFacade.find(id);
        return Optional.ofNullable(skill)
                .map(result -> Response.status(Response.Status.OK).entity(skill).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeSkill(@PathParam("id") Long id) {
        skillFacade.remove(skillFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "skill", id.toString()).build();
    }

}

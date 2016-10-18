package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ISkillService;
import tn.esprit.R2S.model.Skill;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/skill")

public class SkillResource {


    @EJB
    private ISkillService skillService;

    @POST
    public Response createSkill(Skill skill) throws URISyntaxException {

        skillService.create(skill);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/skill/" + skill.getId())),
                "skill", skill.getId().toString())
                .entity(skill).build();
    }

    @PUT
    public Response updateSkill(Skill skill) throws URISyntaxException {

        skillService.edit(skill);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "skill", skill.getId().toString())
                .entity(skill).build();
    }
    @GET
    public List<Skill> getAllSkills() {

        List<Skill> skills = skillService.findAll();
        return skills;
    }

    @Path("/{id}")
    @GET
    public Response getSkill(@PathParam("id") Long id) {

        Skill skill = skillService.find(id);
        return Optional.ofNullable(skill)
                .map(result -> Response.status(Response.Status.OK).entity(skill).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    @DELETE
    public Response removeSkill(@PathParam("id") Long id) {

        skillService.remove(skillService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "skill", id.toString()).build();
    }

}

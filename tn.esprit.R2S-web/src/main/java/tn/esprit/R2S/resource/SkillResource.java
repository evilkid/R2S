package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ISkillService;
import tn.esprit.R2S.model.Skill;
import tn.esprit.R2S.resource.util.Roles;
import tn.esprit.R2S.resource.util.Secured;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/skill")
@Secured(Roles.CHIEF_HUMAN_RESOURCES_OFFICER)
public class SkillResource {

    @EJB
    private ISkillService skillService;

    @POST
    public Response createSkill(Skill skill) throws URISyntaxException {

        skillService.create(skill);
        return Response.created(new URI("/resources/api/skill/" + skill.getId())).entity(skill).build();
    }

    @PUT
    public Response updateSkill(Skill skill) throws URISyntaxException {

        skillService.edit(skill);

        return Response.ok(skill).build();
    }

    @GET
    public List<Skill> getAllSkills() {

        return skillService.findAll();
    }

    @Path("/{id}")
    @GET
    public Response getSkill(@PathParam("id") Long id) {

        return Optional.ofNullable(skillService.find(id))
                .map(skill -> Response.ok(skill).build())
                .orElseThrow(NotFoundException::new);
    }

    @DELETE
    @Path("/{id}")
    public Response removeSkill(@PathParam("id") Long id) {

        return Optional.ofNullable(skillService.find(id))
                .map(skill -> {
                    skillService.remove(skill);
                    return Response.ok().build();
                }).orElseThrow(NotFoundException::new);
    }

}

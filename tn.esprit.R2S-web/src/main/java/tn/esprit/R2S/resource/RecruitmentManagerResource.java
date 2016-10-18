package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IRecruitmentManagerService;
import tn.esprit.R2S.model.RecruitmentManager;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/recruitment-manager")
public class RecruitmentManagerResource {


    @EJB
    private IRecruitmentManagerService recruitmentManagerService;

    @POST
    public Response createRecruitmentManager(RecruitmentManager recruitmentManager) throws URISyntaxException {

        recruitmentManagerService.create(recruitmentManager);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/recruitment-manager/" + recruitmentManager.getCin())),
                "recruitmentManager", recruitmentManager.getCin().toString())
                .entity(recruitmentManager).build();
    }

    @PUT
    public Response updateRecruitmentManager(RecruitmentManager recruitmentManager) throws URISyntaxException {

        recruitmentManagerService.edit(recruitmentManager);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "recruitmentManager", recruitmentManager.getCin().toString())
                .entity(recruitmentManager).build();
    }

    @GET
    public List<RecruitmentManager> getAllRecruitmentManagers() {

        List<RecruitmentManager> recruitmentManagers = recruitmentManagerService.findAll();
        return recruitmentManagers;
    }

    @Path("/{cin}")
    @GET
    public Response getRecruitmentManager(@PathParam("cin") Long cin) {

        RecruitmentManager recruitmentManager = recruitmentManagerService.find(cin);
        return Optional.ofNullable(recruitmentManager)
                .map(result -> Response.status(Response.Status.OK).entity(recruitmentManager).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{cin}")
    @DELETE
    public Response removeRecruitmentManager(@PathParam("cin") Long cin) {

        recruitmentManagerService.remove(recruitmentManagerService.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "recruitmentManager", cin.toString()).build();
    }

}

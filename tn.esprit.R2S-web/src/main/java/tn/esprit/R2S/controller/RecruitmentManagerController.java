package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.RecruitmentManager;
import tn.esprit.R2S.service.RecruitmentManagerFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/recruitment-manager")
public class RecruitmentManagerController {

    @Inject
    private RecruitmentManagerFacade recruitmentManagerFacade;

    @POST
    public Response createRecruitmentManager(RecruitmentManager recruitmentManager) throws URISyntaxException {
        recruitmentManagerFacade.create(recruitmentManager);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/recruitment-manager/" + recruitmentManager.getCin())),
                "recruitmentManager", recruitmentManager.getCin().toString())
                .entity(recruitmentManager).build();
    }

    @PUT
    public Response updateRecruitmentManager(RecruitmentManager recruitmentManager) throws URISyntaxException {
        recruitmentManagerFacade.edit(recruitmentManager);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "recruitmentManager", recruitmentManager.getCin().toString())
                .entity(recruitmentManager).build();
    }

    @GET
    public List<RecruitmentManager> getAllRecruitmentManagers() {
        List<RecruitmentManager> recruitmentManagers = recruitmentManagerFacade.findAll();
        return recruitmentManagers;
    }

    @Path("/{cin}")
    @GET
    public Response getRecruitmentManager(@PathParam("cin") Long cin) {
        RecruitmentManager recruitmentManager = recruitmentManagerFacade.find(cin);
        return Optional.ofNullable(recruitmentManager)
                .map(result -> Response.status(Response.Status.OK).entity(recruitmentManager).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{cin}")
    @DELETE
    public Response removeRecruitmentManager(@PathParam("cin") Long cin) {
        recruitmentManagerFacade.remove(recruitmentManagerFacade.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "recruitmentManager", cin.toString()).build();
    }

}

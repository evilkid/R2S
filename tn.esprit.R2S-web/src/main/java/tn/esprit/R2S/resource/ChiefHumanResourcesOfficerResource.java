package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IChiefHumanResourcesOfficerService;
import tn.esprit.R2S.model.ChiefHumanResourcesOfficer;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/chief-human-resources-officer")
public class ChiefHumanResourcesOfficerResource {


    @EJB
    private IChiefHumanResourcesOfficerService chiefHumanResourcesOfficerService;


    @POST
    public Response createChiefHumanResourcesOfficer(ChiefHumanResourcesOfficer chiefHumanResourcesOfficer) throws URISyntaxException {

        chiefHumanResourcesOfficerService.create(chiefHumanResourcesOfficer);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/chief-human-resources-officer/" + chiefHumanResourcesOfficer.getCin())),
                "chiefHumanResourcesOfficer", chiefHumanResourcesOfficer.getCin().toString())
                .entity(chiefHumanResourcesOfficer).build();
    }


    @PUT
    public Response updateChiefHumanResourcesOfficer(ChiefHumanResourcesOfficer chiefHumanResourcesOfficer) throws URISyntaxException {

        chiefHumanResourcesOfficerService.edit(chiefHumanResourcesOfficer);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "chiefHumanResourcesOfficer", chiefHumanResourcesOfficer.getCin().toString())
                .entity(chiefHumanResourcesOfficer).build();
    }


    @GET
    public List<ChiefHumanResourcesOfficer> getAllChiefHumanResourcesOfficers() {

        List<ChiefHumanResourcesOfficer> chiefHumanResourcesOfficers = chiefHumanResourcesOfficerService.findAll();
        return chiefHumanResourcesOfficers;
    }


    @Path("/{cin}")
    @GET
    public Response getChiefHumanResourcesOfficer(@PathParam("cin") Long cin) {

        ChiefHumanResourcesOfficer chiefHumanResourcesOfficer = chiefHumanResourcesOfficerService.find(cin);
        return Optional.ofNullable(chiefHumanResourcesOfficer)
                .map(result -> Response.status(Response.Status.OK).entity(chiefHumanResourcesOfficer).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }


    @Path("/{cin}")
    @DELETE
    public Response removeChiefHumanResourcesOfficer(@PathParam("cin") Long cin) {

        chiefHumanResourcesOfficerService.remove(chiefHumanResourcesOfficerService.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "chiefHumanResourcesOfficer", cin.toString()).build();
    }

}

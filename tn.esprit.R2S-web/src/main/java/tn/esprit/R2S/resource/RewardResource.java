package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IRewardService;
import tn.esprit.R2S.model.Reward;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@Path("/api/reward")
public class RewardResource {


    @EJB
    private IRewardService rewardService;

    @POST
    public Response createReward(Reward reward) throws URISyntaxException {

        rewardService.create(reward);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/reward/" + reward.getId())),
                "reward", reward.getId().toString())
                .entity(reward).build();
    }

    @PUT
    public Response updateReward(Reward reward) throws URISyntaxException {

        rewardService.edit(reward);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "reward", reward.getId().toString())
                .entity(reward).build();
    }
    @GET
    public List<Reward> getAllRewards() {

        List<Reward> rewards = rewardService.findAll();
        return rewards;
    }

    @Path("/{id}")
    @GET
    public Response getReward(@PathParam("id") Long id) {

        Reward reward = rewardService.find(id);
        return Optional.ofNullable(reward)
                .map(result -> Response.status(Response.Status.OK).entity(reward).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    @Path("/{id}")
    @DELETE
    public Response removeReward(@PathParam("id") Long id) {

        rewardService.remove(rewardService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "reward", id.toString()).build();
    }

}

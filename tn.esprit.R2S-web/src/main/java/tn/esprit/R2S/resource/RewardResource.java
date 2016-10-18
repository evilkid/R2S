package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.Reward;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.RewardService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Reward.
 */
@Path("/api/reward")

public class RewardResource {


    @EJB
    private RewardService rewardService;

    /**
     * POST : Create a new reward.
     *
     * @param reward the reward to create
     * @return the Response with status 201 (Created) and with body the new
     * reward, or with status 400 (Bad Request) if the reward has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createReward(Reward reward) throws URISyntaxException {

        rewardService.create(reward);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/reward/" + reward.getId())),
                "reward", reward.getId().toString())
                .entity(reward).build();
    }

    /**
     * PUT : Updates an existing reward.
     *
     * @param reward the reward to update
     * @return the Response with status 200 (OK) and with body the updated
     * reward, or with status 400 (Bad Request) if the reward is not valid, or
     * with status 500 (Internal Server Error) if the reward couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateReward(Reward reward) throws URISyntaxException {

        rewardService.edit(reward);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "reward", reward.getId().toString())
                .entity(reward).build();
    }

    /**
     * GET : get all the rewards. <% if (pagination != 'no') {} @param pageable
     * the p
     * <p>
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     *
     * @return the Response with status 200 (OK) and the list of rewards in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Reward> getAllRewards() {

        List<Reward> rewards = rewardService.findAll();
        return rewards;
    }

    /**
     * GET /:id : get the "id" reward.
     *
     * @param id the id of the reward to retrieve
     * @return the Response with status 200 (OK) and with body the reward, or
     * with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getReward(@PathParam("id") Long id) {

        Reward reward = rewardService.find(id);
        return Optional.ofNullable(reward)
                .map(result -> Response.status(Response.Status.OK).entity(reward).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" reward.
     *
     * @param id the id of the reward to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeReward(@PathParam("id") Long id) {

        rewardService.remove(rewardService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "reward", id.toString()).build();
    }

}

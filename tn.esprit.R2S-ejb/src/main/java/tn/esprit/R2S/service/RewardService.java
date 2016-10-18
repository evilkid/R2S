package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IRewardService;
import tn.esprit.R2S.model.Reward;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class RewardService extends AbstractService<Reward> implements IRewardService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public RewardService() {
        super(Reward.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

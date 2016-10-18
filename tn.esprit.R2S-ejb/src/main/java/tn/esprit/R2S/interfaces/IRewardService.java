package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Reward;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IRewardService {
    void create(Reward reward);

    Reward edit(Reward reward);

    void remove(Reward reward);

    Reward find(Object id);

    List<Reward> findAll();
}

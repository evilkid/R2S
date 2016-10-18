package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Reward;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IRewardService {
    void create(Reward entity);

    Reward edit(Reward entity);

    void remove(Reward entity);

    Reward find(Object id);

    List<Reward> findAll();
}

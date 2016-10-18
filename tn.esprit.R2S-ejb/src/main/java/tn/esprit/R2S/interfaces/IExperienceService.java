package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Experience;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IExperienceService {
    void create(Experience entity);

    Experience edit(Experience entity);

    void remove(Experience entity);

    Experience find(Object id);

    List<Experience> findAll();
}

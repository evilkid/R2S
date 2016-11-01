package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Experience;

import java.util.List;

/**
 * Created by EvilKids on 10/18/2016.
 */
public interface IExperienceService {
    void create(Experience experience);

    Experience edit(Experience experience);

    void remove(Experience experience);

    Experience find(Object id);

    List<Experience> findAll();
}

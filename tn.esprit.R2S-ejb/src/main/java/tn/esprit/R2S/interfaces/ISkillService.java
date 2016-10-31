package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Skill;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface ISkillService {
    void create(Skill skill);

    Skill edit(Skill skill);

    void remove(Skill skill);

    Skill find(Object id);

    Skill findInitializeJob(Object id);

    List<Skill> findAll();
}

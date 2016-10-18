package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Skill;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface ISkillService {
    void create(Skill entity);

    Skill edit(Skill entity);

    void remove(Skill entity);

    Skill find(Object id);

    List<Skill> findAll();
}

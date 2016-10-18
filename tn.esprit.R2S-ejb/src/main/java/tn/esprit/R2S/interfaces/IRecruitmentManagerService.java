package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.RecruitmentManager;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IRecruitmentManagerService {
    void create(RecruitmentManager entity);

    RecruitmentManager edit(RecruitmentManager entity);

    void remove(RecruitmentManager entity);

    RecruitmentManager find(Object id);

    List<RecruitmentManager> findAll();
}

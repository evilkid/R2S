package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.RecruitmentManager;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IRecruitmentManagerService {
    void create(RecruitmentManager recruitmentManager);

    RecruitmentManager edit(RecruitmentManager recruitmentManager);

    void remove(RecruitmentManager recruitmentManager);

    RecruitmentManager find(Object id);

    RecruitmentManager findInitializeInterviews(Object id);

    List<RecruitmentManager> findAll();
}

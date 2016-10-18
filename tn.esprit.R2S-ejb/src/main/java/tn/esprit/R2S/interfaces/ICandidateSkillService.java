package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.CandidateSkill;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICandidateSkillService {
    void create(CandidateSkill entity);

    CandidateSkill edit(CandidateSkill entity);

    void remove(CandidateSkill entity);

    CandidateSkill find(Object id);

    List<CandidateSkill> findAll();
}

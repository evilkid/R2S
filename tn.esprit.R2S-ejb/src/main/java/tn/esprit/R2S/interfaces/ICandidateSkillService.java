package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.CandidateSkill;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICandidateSkillService {
    void create(CandidateSkill candidateSkill);

    CandidateSkill edit(CandidateSkill candidateSkill);

    void remove(CandidateSkill candidateSkill);

    CandidateSkill find(Object id);

    List<CandidateSkill> findAll();
}

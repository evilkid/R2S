package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Candidate;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICandidateService {
    void create(Candidate candidate);

    Candidate edit(Candidate candidate);

    void remove(Candidate candidate);

    Candidate find(Object id);

    List<Candidate> findAll();

    List<Candidate> findBySkillId(int skillId);
}

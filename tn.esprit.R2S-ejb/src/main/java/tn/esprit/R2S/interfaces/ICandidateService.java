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

    Candidate findInitializeJobs(Object id);

    List<Candidate> findAll();

    List<Candidate> findBySkillId(Long skillId);

    List<Candidate> findByExperience(int duration);

    List<Candidate> findByExperienceBetween(int duration1, int duration2);

    List<Candidate> findByCertification(String certificateName);
}

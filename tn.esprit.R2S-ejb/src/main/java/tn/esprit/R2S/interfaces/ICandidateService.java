package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Candidate;
import tn.esprit.R2S.model.Certification;

import javax.ejb.Local;
import java.util.List;
import java.util.Set;

@Local
public interface ICandidateService {
    void create(Candidate candidate);

    Candidate edit(Candidate candidate);

    void remove(Candidate candidate);

    Candidate find(Object id);

    Candidate findInitializeJobs(Object id);

    List<Candidate> findAll();

    List<Candidate> findBySkillId(int skillId);

    Set<Candidate> findByExperience(int duration);

    Set<Candidate> findByExperienceBetween(int duration1, int duration2);

    List<Candidate> findByCertification(Certification certification);
}

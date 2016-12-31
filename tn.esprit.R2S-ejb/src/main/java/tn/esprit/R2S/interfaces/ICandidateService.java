package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Candidate;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ICandidateService {
    void create(Candidate candidate);

    Candidate edit(Candidate candidate);

    void remove(Candidate candidate);

    Candidate find(Object id);

    Candidate findInitializeJobs(Object id);

    List<Candidate> findAll();

    Candidate findInitializeInterviews(Object id);

    Candidate findInitializeCertifications(Object id);

    List<Candidate> findBySkillId(Long skillId);

    List<Candidate> findByExperience(int duration);

    List<Candidate> findByExperienceBetween(int duration1, int duration2);

    List<Candidate> findByCertification(String certificateName);
}

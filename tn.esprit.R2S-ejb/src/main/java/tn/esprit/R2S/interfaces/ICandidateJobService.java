package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.CandidateJob;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICandidateJobService {
    void create(CandidateJob candidateJob);

    CandidateJob edit(CandidateJob candidateJob);

    void remove(CandidateJob candidateJob);

    CandidateJob find(Object id);

    List<CandidateJob> findAll();
}

package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.CandidateJob;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICandidateJobService {
    void create(CandidateJob entity);

    CandidateJob edit(CandidateJob entity);

    void remove(CandidateJob entity);

    CandidateJob find(Object id);

    List<CandidateJob> findAll();
}

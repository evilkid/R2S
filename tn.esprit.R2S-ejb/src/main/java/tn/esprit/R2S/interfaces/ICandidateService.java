package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Candidate;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICandidateService {
    void create(Candidate entity);

    Candidate edit(Candidate entity);

    void remove(Candidate entity);

    Candidate find(Object id);

    List<Candidate> findAll();
}

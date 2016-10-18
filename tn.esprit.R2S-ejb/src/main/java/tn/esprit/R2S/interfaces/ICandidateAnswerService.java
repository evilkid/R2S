package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.CandidateAnswer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICandidateAnswerService {
    void create(CandidateAnswer entity);

    CandidateAnswer edit(CandidateAnswer entity);

    void remove(CandidateAnswer entity);

    CandidateAnswer find(Object id);

    List<CandidateAnswer> findAll();
}

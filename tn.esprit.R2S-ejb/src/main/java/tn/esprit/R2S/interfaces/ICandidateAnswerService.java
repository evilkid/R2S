package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.CandidateAnswer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICandidateAnswerService {
    void create(CandidateAnswer candidateAnswer);

    CandidateAnswer edit(CandidateAnswer candidateAnswer);

    void remove(CandidateAnswer candidateAnswer);

    CandidateAnswer find(Object id);

    List<CandidateAnswer> findAll();
}

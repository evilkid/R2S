package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Candidate;
import tn.esprit.R2S.model.CandidateField;
import tn.esprit.R2S.model.CandidateFieldValue;

import java.util.List;

/**
 * Created by EvilKids on 10/30/2016.
 */
public interface ICandidateFieldService {
    void create(CandidateField candidateField);

    CandidateField edit(CandidateField candidateField);

    void remove(CandidateField candidateField);

    CandidateField find(Object id);

    CandidateField findByName(String fieldName);

    List<CandidateField> findAll();

    CandidateFieldValue findValue(CandidateField candidateField, Candidate candidate);
}

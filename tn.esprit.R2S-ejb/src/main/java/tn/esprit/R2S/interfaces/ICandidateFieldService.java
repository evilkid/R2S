package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.CandidateField;

import java.util.List;

/**
 * Created by evilkid on 10/30/2016.
 */
public interface ICandidateFieldService {
    void create(CandidateField candidateField);

    CandidateField edit(CandidateField candidateField);

    void remove(CandidateField candidateField);

    CandidateField find(Object id);

    CandidateField findByName(String fieldName);

    List<CandidateField> findAll();

}
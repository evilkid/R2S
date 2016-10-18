package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.CandidateQuizModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICandidateQuizModelService {
    void create(CandidateQuizModel entity);

    CandidateQuizModel edit(CandidateQuizModel entity);

    void remove(CandidateQuizModel entity);

    CandidateQuizModel find(Object id);

    List<CandidateQuizModel> findAll();
}

package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.CandidateQuizModel;
import tn.esprit.R2S.model.Question;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface ICandidateQuizModelService {
    void create(CandidateQuizModel candidateQuizModel);

    CandidateQuizModel edit(CandidateQuizModel candidateQuizModel);

    void remove(CandidateQuizModel candidateQuizModel);

    CandidateQuizModel find(Object id);

    List<CandidateQuizModel> findAll();

    double calculateScore(CandidateQuizModel candidateQuizModel);

    double calculateSingleQuestionNote(CandidateQuizModel candidateQuizModel, Question question);

    Map<CandidateQuizModel, Double> getHistorique();
}

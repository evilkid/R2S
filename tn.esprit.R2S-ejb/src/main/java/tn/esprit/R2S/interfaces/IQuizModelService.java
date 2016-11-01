package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.QuizModel;

import java.util.List;

/**
 * Created by EvilKids on 10/18/2016.
 */
public interface IQuizModelService {
    void create(QuizModel quizModel);

    QuizModel edit(QuizModel quizModel);

    void remove(QuizModel quizModel);

    QuizModel find(Object id);

    List<QuizModel> findAll();

    void addRandomQuizModel(QuizModel quizModel, Long id);
}

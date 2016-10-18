package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.QuizModel;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IQuizModelService {
    void create(QuizModel entity);

    QuizModel edit(QuizModel entity);

    void remove(QuizModel entity);

    QuizModel find(Object id);

    List<QuizModel> findAll();
}

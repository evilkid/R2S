package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Question;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IQuestionService {
    void create(Question entity);

    Question edit(Question entity);

    void remove(Question entity);

    Question find(Object id);

    List<Question> findAll();
}

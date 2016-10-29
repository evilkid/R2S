package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Question;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IQuestionService {
    void create(Question question);

    Question edit(Question question);

    void remove(Question question);

    Question find(Object id);

    List<Question> findAll();

    List<Question> findRandomQuestions(int numberOfQuestions);

    List<Question> findRandomQuestionsByCategory(int numberOfQuestions, Long categoryId);
}

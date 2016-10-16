package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Answer;

import java.util.List;

public interface IAnswerFacade {
    void create(Answer entity);

    Answer edit(Answer entity);

    void remove(Answer entity);

    Answer find(Object id);

    List<Answer> findAll();

    int count();
}

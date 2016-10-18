package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Answer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IAnswerFacade {
    void create(Answer entity);

    Answer edit(Answer entity);

    void remove(Answer entity);

    Answer find(Object id);

    List<Answer> findAll();

    int count();
}

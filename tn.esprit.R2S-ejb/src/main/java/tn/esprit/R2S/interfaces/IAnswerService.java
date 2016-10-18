package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Answer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IAnswerService {
    void create(Answer answer);

    Answer edit(Answer answer);

    void remove(Answer answer);

    Answer find(Object id);

    List<Answer> findAll();

}

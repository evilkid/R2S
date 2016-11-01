package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Interview;

import java.util.List;

/**
 * Created by EvilKids on 10/18/2016.
 */
public interface IInterviewService {
    void create(Interview interview);

    Interview edit(Interview interview);

    void remove(Interview interview);

    Interview find(Object id);

    List<Interview> findAll();
}

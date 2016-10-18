package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Interview;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IInterviewService {
    void create(Interview entity);

    Interview edit(Interview entity);

    void remove(Interview entity);

    Interview find(Object id);

    List<Interview> findAll();
}

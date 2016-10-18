package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Education;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IEducationService {
    void create(Education education);

    Education edit(Education education);

    void remove(Education education);

    Education find(Object id);

    List<Education> findAll();
}

package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Certification;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICertificationService {
    void create(Certification entity);

    Certification edit(Certification entity);

    void remove(Certification entity);

    Certification find(Object id);

    List<Certification> findAll();
}

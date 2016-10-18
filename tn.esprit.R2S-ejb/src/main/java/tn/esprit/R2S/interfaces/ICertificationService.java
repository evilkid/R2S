package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Certification;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICertificationService {
    void create(Certification certification);

    Certification edit(Certification certification);

    void remove(Certification certification);

    Certification find(Object id);

    List<Certification> findAll();
}

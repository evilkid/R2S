package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.ChiefHumanResourcesOfficer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IChiefHumanResourcesOfficerService {
    void create(ChiefHumanResourcesOfficer entity);

    ChiefHumanResourcesOfficer edit(ChiefHumanResourcesOfficer entity);

    void remove(ChiefHumanResourcesOfficer entity);

    ChiefHumanResourcesOfficer find(Object id);

    List<ChiefHumanResourcesOfficer> findAll();
}

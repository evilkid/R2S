package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.ChiefHumanResourcesOfficer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IChiefHumanResourcesOfficerService {
    void create(ChiefHumanResourcesOfficer chiefHumanResourcesOfficer);

    ChiefHumanResourcesOfficer edit(ChiefHumanResourcesOfficer chiefHumanResourcesOfficer);

    void remove(ChiefHumanResourcesOfficer chiefHumanResourcesOfficer);

    ChiefHumanResourcesOfficer find(Object id);

    List<ChiefHumanResourcesOfficer> findAll();
}

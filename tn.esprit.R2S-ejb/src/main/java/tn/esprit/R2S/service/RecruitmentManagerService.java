package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IRecruitmentManagerService;
import tn.esprit.R2S.model.RecruitmentManager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class RecruitmentManagerService extends AbstractService<RecruitmentManager> implements IRecruitmentManagerService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public RecruitmentManagerService() {
        super(RecruitmentManager.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

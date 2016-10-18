package tn.esprit.R2S.service;

import tn.esprit.R2S.model.RecruitmentManager;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("recruitmentManager")
public class RecruitmentManagerService extends AbstractService<RecruitmentManager> {

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

package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.ICandidateSkillService;
import tn.esprit.R2S.model.CandidateSkill;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class CandidateSkillService extends AbstractService<CandidateSkill> implements ICandidateSkillService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public CandidateSkillService() {
        super(CandidateSkill.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

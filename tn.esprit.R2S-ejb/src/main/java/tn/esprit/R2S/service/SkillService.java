package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.ISkillService;
import tn.esprit.R2S.model.Skill;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class SkillService extends AbstractService<Skill> implements ISkillService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public SkillService() {
        super(Skill.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Skill findInitializeJob(Object id) {
        Skill skill = find(id);
        skill.getJobs().size();

        return skill;
    }
}

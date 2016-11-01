package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.ICandidateService;
import tn.esprit.R2S.model.Candidate;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless

public class CandidateService extends AbstractService<Candidate> implements ICandidateService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public CandidateService() {
        super(Candidate.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Candidate findInitializeJobs(Object id) {
        Candidate candidate = find(id);
        candidate.getJobs().size();
        return candidate;
    }

    @Override
    public List<Candidate> findBySkillId(int skillId) {
        System.out.println(skillId);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Candidate> c = cb.createQuery(Candidate.class);
        Root<Candidate> candidate = c.from(Candidate.class);
        c.select(candidate)
                .where(cb.equal(
                        candidate
                                .join("candidateSkills")
                                .join("skill")
                                .get("id"),
                        skillId)
                );
        try {
            return em.createQuery(c).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}

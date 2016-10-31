package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IJobService;
import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.util.enums.JobStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless

public class JobService extends AbstractService<Job> implements IJobService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public JobService() {
        super(Job.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Job findInitializeRewards(Object id) {
        Job job = find(id);
        job.getRewards().size();
        return job;
    }

    @Override
    public Job findInitializeSkills(Object id) {
        Job job = find(id);
        job.getSkills().size();
        return job;
    }

    @Override
    public List<Job> findByStatus(JobStatus jobStatus) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Job> query = criteriaBuilder.createQuery(Job.class);
        Root<Job> jobRoot = query.from(Job.class);
        query.select(jobRoot)
                .where(criteriaBuilder.equal(jobRoot.get("status"), jobStatus));
        try {
            return em.createQuery(query).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Job> findBySkill(Long skillId) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Job> query = criteriaBuilder.createQuery(Job.class);
        Root<Job> jobRoot = query.from(Job.class);
        query.select(jobRoot)
                .where(criteriaBuilder.equal(jobRoot.join("skills").get("id"), skillId));
        try {
            return em.createQuery(query).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}

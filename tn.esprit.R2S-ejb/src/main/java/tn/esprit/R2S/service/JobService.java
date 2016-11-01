package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.ICandidateService;
import tn.esprit.R2S.interfaces.IJobService;
import tn.esprit.R2S.model.Candidate;
import tn.esprit.R2S.model.CandidateSkill;
import tn.esprit.R2S.model.Experience;
import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.util.enums.JobStatus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless

public class JobService extends AbstractService<Job> implements IJobService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    @EJB
    private ICandidateService candidateService;

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

    @Override
    public List<Candidate> findCandidates(Long jobId) {

        /*
         select SK.candidate_cin
         from skill_job SJ
         JOIN candidateskill SK ON SK.skill_id = SJ.skills_id
         WHERE SJ.jobs_id = 2
         GROUP BY SK.candidate_cin
         */

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CandidateSkill> query = criteriaBuilder.createQuery(CandidateSkill.class);
        Root<CandidateSkill> candidateSkillRoot = query.from(CandidateSkill.class);

        Join jobsJoin = candidateSkillRoot.join("skill").join("jobs");

        query.select(candidateSkillRoot.get("candidate"));
        query.where(criteriaBuilder.equal(jobsJoin.get("id"), jobId));

        query.distinct(true);

        List list = em.createQuery(query).getResultList();

        list.sort((Object o1, Object o2) -> {
            Candidate c1 = (Candidate) o1;
            Candidate c2 = (Candidate) o2;
            Long c1ExpDays = c1.getExperiences().stream().mapToLong(Experience::getDifferenceInDays).sum();
            Long c2ExpDays = c2.getExperiences().stream().mapToLong(Experience::getDifferenceInDays).sum();
            return c2ExpDays.intValue() - c1ExpDays.intValue();
        });

        return list;
    }
}

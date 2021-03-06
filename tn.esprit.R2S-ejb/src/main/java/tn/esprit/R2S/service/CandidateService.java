package tn.esprit.R2S.service;

import org.apache.commons.codec.digest.DigestUtils;
import tn.esprit.R2S.interfaces.ICandidateJobService;
import tn.esprit.R2S.interfaces.ICandidateService;
import tn.esprit.R2S.interfaces.IReferHashService;
import tn.esprit.R2S.model.*;
import tn.esprit.R2S.util.enums.Progress;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.List;

@Stateless
public class CandidateService extends AbstractService<Candidate> implements ICandidateService {


    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    @EJB
    private ICandidateJobService candidateJobService;

    @EJB
    private IReferHashService referHashService;


    public CandidateService() {
        super(Candidate.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void register(String hash, Candidate candidate) {
        ReferHash referHash = referHashService.findByHash(hash);
        if (referHash != null) {
            candidate.setReferee(referHash.getEmployee());

            candidate.setPassword(DigestUtils.md5Hex(candidate.getPassword()));
            create(candidate);

            CandidateJob candidateJob = new CandidateJob();

            candidateJob.setCandidate(candidate);
            candidateJob.setJob(referHash.getJob());

            candidateJob.setCandidateJob(new CandidateJobPK(referHash.getJob().getId(), candidate.getCin()));
            candidateJob.setProgress(Progress.STARTED);
            candidateJob.setDate(new Date());

            candidateJobService.create(candidateJob);

            Reward reward = new Reward();


        } else {
            throw new NotFoundException("Hash not found");
        }
    }

    @Override
    public Candidate findInitializeJobs(Object id) {
        Candidate candidate = find(id);
        candidate.getJobs().size();
        return candidate;
    }

    @Override
    public Candidate findInitializeInterviews(Object id) {
        Candidate candidate = find(id);
        candidate.getInterviews().size();
        return candidate;
    }


    @Override
    public Candidate findInitializeCertifications(Object id) {
        Candidate candidate = find(id);
        candidate.getCertifications().size();
        return candidate;
    }

    @Override
    public Candidate findInitializeExperiences(Object id) {
        Candidate candidate = find(id);
        candidate.getExperiences().size();
        return candidate;
    }

    @Override
    public Candidate findInitializeEducations(Object id) {
        Candidate candidate = find(id);
        candidate.getEducations().size();
        return candidate;
    }

    @Override
    public Candidate findInitializeSkills(Object id) {
        Candidate candidate = find(id);
        candidate.getCandidateSkills().size();
        return candidate;
    }

    @Override
    public List<Candidate> findBySkillId(Long skillId) {
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

    /**
     * @param duration in days
     * @return list of candidates which have more than duration days of experience
     */
    @Override
    public List<Candidate> findByExperience(int duration) {

        return findByExperience(duration, null);


    }


    @Override
    public List<Candidate> findByExperienceBetween(int duration1, int duration2) {

        return findByExperience(duration1, duration2);

    }


    private List<Candidate> findByExperience(Integer infBound, Integer supBound) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Candidate> query = cb.createQuery(Candidate.class);
        Root<Candidate> candidateRoot = query.from(Candidate.class);

        Join join = candidateRoot.join("experiences");

        Expression sum = cb.sum(
                cb.diff(join.get("dateEnd"), join.get("dateStart"))
        );

        query.multiselect(candidateRoot, sum);

        if (supBound != null) {
            query.having(cb.between(sum, infBound, supBound));
        } else {
            query.having(cb.ge(sum, infBound));
        }

        query.groupBy(candidateRoot);
        query.select(candidateRoot);

        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Candidate> findByCertification(String certificateName) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Candidate> query = cb.createQuery(Candidate.class);
        Root<Candidate> candidateRoot = query.from(Candidate.class);

        Join join = candidateRoot.join("certifications");

        Expression condition = cb.like(join.get("name"), "%" + certificateName + "%");

        query.where(condition);
        query.groupBy(candidateRoot);
        query.select(candidateRoot);

        return em.createQuery(query).getResultList();
    }


}

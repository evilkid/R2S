package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.ICandidateService;
import tn.esprit.R2S.model.Candidate;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    /**
     * @param duration in days
     * @return list of candidates which have more than duration days of experience
     */
    @Override
    public List<Candidate> findByExperience(int duration) {

        return findByExperience(duration, null);

        /*CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Candidate> c = cb.createQuery(Candidate.class);
        Root<Candidate> candidate = c.from(Candidate.class);

        Join join = candidate.join("experiences");

        Expression sum = cb.sum(
                cb.diff(join.get("dateEnd"), join.get("dateStart"))
        );

        c.multiselect(candidate, sum).having(cb.ge(sum, duration)).groupBy(candidate).select(candidate);


        return em.createQuery(c).getResultList();*/
        /*
        String query = "SELECT q FROM Experience q ";
        Query q = em.createQuery(query);
        List<Experience> experiences = q.getResultList();
        Set<Candidate> candidates = new HashSet<>();
        for (Experience e : experiences
                ) {

            try {
                int d = nbOfMonthsBetweenTwoDates(e.getDateEnd(), e.getDateStart());
                if (d >= duration)
                    candidates.add(e.getCandidate());

            } catch (Exception v) {
                v.printStackTrace();

            }
        }
        return candidates;*/
    }


    @Override
    public List<Candidate> findByExperienceBetween(int duration1, int duration2) {

        return findByExperience(duration1, duration2);


        /*CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Candidate> c = cb.createQuery(Candidate.class);
        Root<Candidate> candidate = c.from(Candidate.class);

        Join join = candidate.join("experiences");

        Expression sum = cb.sum(
                cb.diff(join.get("dateEnd"), join.get("dateStart"))
        );

        c.multiselect(candidate, sum);
        c.having(cb.between(sum, duration1, duration2));
        c.groupBy(candidate);
        c.select(candidate);


        return em.createQuery(c).getResultList();*/
/*
        String query = "SELECT q FROM Experience q ";
        Query q = em.createQuery(query);
        List<Experience> experiences = q.getResultList();
        Set<Candidate> candidates = new HashSet<>();
        try {
            for (Experience e : experiences
                    ) {

                int duration = nbOfMonthsBetweenTwoDates(e.getDateEnd(), e.getDateStart());
                if ((duration >= duration1) && (duration <= duration2)) {
                    candidates.add(e.getCandidate());
                }

            }

        } catch (Exception v) {
            v.printStackTrace();

        }

        return candidates;*/
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

        String search = "%" + certificateName + "%";
        System.out.println(search);
        Expression condition = cb.like(join.get("name"), search);

        query.where(condition);
        query.groupBy(candidateRoot);
        query.select(candidateRoot);

        return em.createQuery(query).getResultList();
        /*
        String query = "SELECT q FROM Candidate q";
        Query q = em.createQuery(query);
        List<Candidate> candidates = q.getResultList();
        List<Candidate> result = new ArrayList<>();

        try {
            for (Candidate e : candidates) {
                if (e.getCertifications().stream().filter(d -> d.getId() == certification.getId()).count() != 0)

                {
                    result.add(e);
                }
            }
        } catch (Exception v) {
            v.printStackTrace();

        }
        return result;*/
    }


    private int nbOfMonthsBetweenTwoDates(Date dateString1, Date dateString2) throws Exception {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(dateString1);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(dateString2);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);

        System.out.println("mois");
        System.out.println(diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH));
        return diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
    }

}

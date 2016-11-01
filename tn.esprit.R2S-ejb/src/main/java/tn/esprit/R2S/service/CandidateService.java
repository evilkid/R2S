package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.ICandidateService;
import tn.esprit.R2S.model.Candidate;
import tn.esprit.R2S.model.Certification;
import tn.esprit.R2S.model.Experience;
import tn.esprit.R2S.model.ReferHash;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

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

    //month
    @Override
    public Set<Candidate> findByExperience(int duration) {

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
        return candidates;
    }


    @Override
    public Set<Candidate> findByExperienceBetween(int duration1, int duration2) {
        String query = "SELECT q FROM Experience q ";
        Query q = em.createQuery(query);
        List<Experience> experiences = q.getResultList();
        Set<Candidate> candidates = new HashSet<>();
        try {
            for (Experience e : experiences
                    ) {

                int duration = nbOfMonthsBetweenTwoDates(e.getDateEnd(), e.getDateStart());
                if ((duration >= duration1) && (duration <= duration2))
                    candidates.add(e.getCandidate());

            }

        } catch (Exception v) {
            v.printStackTrace();

        }

        return candidates;
    }

    @Override
    public List<Candidate> findByCertification(Certification certification) {
        String query = "SELECT q FROM Candidate q";
        Query q = em.createQuery(query);
        List<Candidate> candidates = q.getResultList();
        List<Candidate> result = new ArrayList<>();

        try {
            for (Candidate e : candidates
                    ) {
                if (e.getCertifications().stream().filter(d -> d.getId() == certification.getId()).count() != 0)

                    result.add(e);
            }
        } catch (Exception v) {
            v.printStackTrace();

        }
        return result;
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

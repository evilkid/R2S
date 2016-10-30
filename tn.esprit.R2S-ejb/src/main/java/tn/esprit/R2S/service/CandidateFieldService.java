package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.ICandidateFieldService;
import tn.esprit.R2S.model.CandidateField;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by evilkid on 10/30/2016.
 */
@Stateless
public class CandidateFieldService extends AbstractService<CandidateField> implements ICandidateFieldService {
    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public CandidateFieldService() {
        super(CandidateField.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public CandidateField findByName(String fieldName) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CandidateField> query = criteriaBuilder.createQuery(CandidateField.class);
        Root<CandidateField> candidateFieldRoot = query.from(CandidateField.class);

        query
                .select(candidateFieldRoot)
                .where(criteriaBuilder.equal(
                        candidateFieldRoot.get("fieldName"), fieldName)
                );

        try {
            return em.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

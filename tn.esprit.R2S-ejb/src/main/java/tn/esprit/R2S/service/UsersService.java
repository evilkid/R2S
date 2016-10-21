package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IUsersService;
import tn.esprit.R2S.model.Users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless

public class UsersService extends AbstractService<Users> implements IUsersService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public UsersService() {
        super(Users.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Users login(String username, String password) {
        System.out.println("user: " + username);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Users> c = cb.createQuery(Users.class);
        Root<Users> emp = c.from(Users.class);
        c.select(emp)
                .where(cb.equal(emp.get("username"), username),
                        cb.equal(emp.get("password"), password));

        try {
            return em.createQuery(c).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

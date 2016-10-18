package tn.esprit.R2S.service;

import tn.esprit.R2S.model.Users;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("users")
public class UsersService extends AbstractService<Users> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public UsersService() {
        super(Users.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

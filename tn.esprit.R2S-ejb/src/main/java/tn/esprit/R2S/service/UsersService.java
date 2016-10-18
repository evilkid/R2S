package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IUsersService;
import tn.esprit.R2S.model.Users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}

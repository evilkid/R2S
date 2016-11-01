package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Candidate;
import tn.esprit.R2S.model.Users;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by EvilKids on 10/18/2016.
 */
@Remote //just for tests
public interface IUsersService {
    void create(Users users);

    Users edit(Users users);

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();

    Users login(String username, String password);

    void disable(Long cin);

    void enable(Long cin);

    List<Candidate> getReferred(Long cin);
}

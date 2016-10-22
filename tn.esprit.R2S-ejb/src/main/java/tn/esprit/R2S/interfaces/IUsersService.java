package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Users;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IUsersService {
    void create(Users users);

    Users edit(Users users);

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();

    Users login(String username, String password);

    void disable(Long cin);

    void enable(Long cin);
}

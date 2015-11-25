package com.foodniche.db.dao;

import com.foodniche.db.entities.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by agdubrov on 11/25/15.
 */

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager em;

    public Users findUserByName(String name) {
        List<Users> users = em.createNamedQuery("Users.findByUsername", Users.class).setParameter("username", name).getResultList();

        return users.size() > 0 ? users.get(0) : null;
    }
}

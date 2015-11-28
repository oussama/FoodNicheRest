package com.foodniche.db.dao;

import com.foodniche.db.entities.Users;
import com.foodniche.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by agdubrov on 11/25/15.
 */

@Repository
public class UserDao extends BaseDao<Users, Integer> {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepo;

    @Override
    protected JpaRepository<Users, Integer> getRepository() {
        return userRepo;
    }

    public Users findUserByName(String name) {
        List<Users> users = em.createNamedQuery("Users.findByUsername", Users.class).setParameter("username", name).getResultList();

        return users.size() > 0 ? users.get(0) : null;
    }
}

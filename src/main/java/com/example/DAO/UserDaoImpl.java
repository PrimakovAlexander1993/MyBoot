package com.example.DAO;

import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public void addUser(User user) { //
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {//
        entityManager.merge(user);
    }
    @Transactional
    @Override
    public void removeUser(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
    @Transactional
    @Override
    public User getUserById(int id) {
        TypedQuery<User> query = entityManager.createQuery("FROM User WHERE id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query = entityManager
                .createQuery("FROM User WHERE login = :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
       return entityManager.createQuery("FROM User").getResultList();
    }
}

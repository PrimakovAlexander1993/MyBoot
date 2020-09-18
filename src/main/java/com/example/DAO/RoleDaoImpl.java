package com.example.DAO;

import com.example.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    EntityManager entityManager;

    @Transactional
    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
    @Transactional
    @Override
    public Role getRoleByName(String role) {
        TypedQuery<Role> query = entityManager.createQuery("FROM Role where role = :role", Role.class);
        query.setParameter("role", role);
        return query.getSingleResult();
    }
    @Transactional
    @Override
    public Long countRoles(String name) {                                   //where name = :name"
        return(Long) entityManager.createQuery("SELECT COUNT (*) FROM Role where role = :role")
                .setParameter("role", name).getSingleResult();
    }                             //name

}

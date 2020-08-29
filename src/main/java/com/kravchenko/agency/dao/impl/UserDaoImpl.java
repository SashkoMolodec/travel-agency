package com.kravchenko.agency.dao.impl;

import com.kravchenko.agency.dao.UserDao;
import com.kravchenko.agency.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        TypedQuery<User> query = session.createQuery("from User where username = :name");
        query.setParameter("name", username);

        try {
            User user = query.getSingleResult();
            return Optional.of(user);
        } catch (NoResultException nre) {
            return Optional.empty();
        } finally {
            session.getTransaction().commit();
        }
    }


}
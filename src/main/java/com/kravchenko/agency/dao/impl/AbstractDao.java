package com.kravchenko.agency.dao.impl;

import com.kravchenko.agency.dao.Dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements Dao<T> {

    Class<T> clazz;
    final SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    @Override
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        try {
            @SuppressWarnings("unchecked")
            TypedQuery<T> query = session.createQuery("from " + clazz.getName());
            return query.getResultList();
        } catch (NullPointerException npe) {
            return new ArrayList<>();
        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public void save(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        sessionFactory.getCurrentSession().save(t);
        session.getTransaction().commit();
    }

    @Override
    public void update(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        sessionFactory.getCurrentSession().update(t);
        session.getTransaction().commit();
    }

    @Override
    public Optional<T> findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            T t = session.get(clazz, id);
            return Optional.of(t);
        } catch (NullPointerException npe) {
            return Optional.empty();
        } finally {
            session.getTransaction().commit();
        }
    }

}

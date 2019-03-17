package org.courses.DAO.hbm;

import org.apache.commons.validator.routines.IntegerValidator;
import org.courses.DAO.DAO;
import org.courses.domain.hbm.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class TypeDao implements DAO<Type, Integer> {
    private IntegerValidator Int32 = IntegerValidator.getInstance();
    private SessionFactory factory;

    public TypeDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void save(Collection<Type> entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            saveTypes(session, transaction, entity);
        }
        catch (Exception e) {
            if (null != transaction)
                transaction.rollback();
            throw e;
        }
        finally {
            if (null != session)
                session.close();
        }
    }

    private void saveTypes(Session session, Transaction transaction, Collection<Type> entity) {
        for (Type type : entity) {
            session.save(type);
        }
        transaction.commit();
    }

    @Override
    public Type read(Integer id) {
        Type result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session.find(Type.class, id);
        }
        finally {
            if (null != session)
                session.close();
        }
        return result;
    }

    @Override
    public Collection<Type> readAll() {
        Collection<Type> result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session
                    .createQuery("from Type")
                    .list();
        }
        finally {
            if (null != session)
                session.close();
        }
        return result;
    }

    @Override
    public Collection<Type> find(String filter) {
        List result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session
                    .createQuery("from Type " +
                            "where id = :id " +
                            "or name like :filter")
                    .setParameter("id", Int32.validate(filter))
                    .setParameter("filter", String.format("%%%s%%", filter))
                    .list();
        }
        finally {
            if (null != session)
                session.close();
        }
        return result;
    }

    @Override
    public void delete(Type entity) {
        Session session = null;
        try {
            session = factory.openSession();
            session.delete(entity);
        }
        finally {
            if (null != session)
                session.close();
        }
    }
}

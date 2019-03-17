package org.courses.DAO.hbm;

import org.apache.commons.validator.routines.IntegerValidator;
import org.courses.DAO.DAO;
import org.courses.domain.hbm.Manufacture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class ManufactureDao implements DAO<Manufacture, Integer> {
    private IntegerValidator Int32 = IntegerValidator.getInstance();
    private SessionFactory factory;

    public ManufactureDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void save(Collection<Manufacture> entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            saveManufactures(session, transaction, entity);
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
    private void saveManufactures(Session session, Transaction transaction, Collection<Manufacture> entity) {
        for (Manufacture Manufacture : entity) {
            session.save(Manufacture);
        }
        transaction.commit();
    }

    @Override
    public Manufacture read(Integer id) {
        Manufacture result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session.find(Manufacture.class, id);
        }
        finally {
            if (null != session)
                session.close();
        }
        return result;
    }

    @Override
    public Collection<Manufacture> readAll() {
        Collection<Manufacture> result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session
                    .createQuery("from Manufacture")
                    .list();
        }
        finally {
            if (null != session)
                session.close();
        }
        return result;
    }

    @Override
    public Collection<Manufacture> find(String filter) {
        List result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session
                    .createQuery("from Manufacture " +
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
    public void delete(Manufacture entity) {
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

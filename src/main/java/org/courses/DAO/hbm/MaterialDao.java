package org.courses.DAO.hbm;

import org.apache.commons.validator.routines.IntegerValidator;
import org.courses.DAO.DAO;
import org.courses.domain.hbm.Material;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.List;

public class MaterialDao implements DAO<Material, Integer> {
    private IntegerValidator Int32 = IntegerValidator.getInstance();
    private SessionFactory factory;

    public MaterialDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void save(Collection<Material> entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            saveMaterials(session, transaction, entity);
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

    private void saveMaterials(Session session, Transaction transaction, Collection<Material> entity) {
        for (Material material : entity) {
            session.save(material);
        }
        transaction.commit();
    }

    @Override
    public Material read(Integer id) {
        Material result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session.find(Material.class, id);
        }
        finally {
            if (null != session)
                session.close();
        }
        return result;
    }

    @Override
    public Collection<Material> readAll() {
        Collection<Material> result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session
                    .createQuery("from Material")
                    .list();
        }
        finally {
            if (null != session)
                session.close();
        }
        return result;
    }

    @Override
    public Collection<Material> find(String filter) {
        List result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session
                    .createQuery("from Material " +
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
    public void delete(Collection<Material> entitys) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            deleteMaterials(session,transaction,entitys);
        }
        catch (Exception e){
            if (null != transaction)
                transaction.rollback();
            throw e;
        }
        finally {
            if (null != session)
                session.close();
        }
    }

    private void deleteMaterials(Session session, Transaction transaction, Collection<Material> entitys) {
        for (Material material : entitys) {
            session.delete(material);
        }
        transaction.commit();
    }
}

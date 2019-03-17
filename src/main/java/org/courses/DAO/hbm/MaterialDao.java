package org.courses.DAO.hbm;

import org.courses.DAO.DAO;
import org.courses.domain.hbm.Material;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

public class MaterialDao implements DAO<Material, Integer> {
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
        throw new NotImplementedException();
    }

    @Override
    public Collection<Material> readAll() {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Material> find(String filter) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Material entity) {
        throw new NotImplementedException();
    }
}

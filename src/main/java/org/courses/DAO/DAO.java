package org.courses.DAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface DAO<Tentity, Tkey> {
   void save(Collection<Tentity> entitys);

   Tentity read(Tkey id);

   Collection<Tentity> readAll();

   Collection<Tentity> find(String filter);

   void delete(Collection<Tentity> entitys);
}

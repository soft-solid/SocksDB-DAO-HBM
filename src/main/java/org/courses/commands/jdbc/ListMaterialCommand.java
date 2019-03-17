package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.domain.hbm.Material;

import java.util.Collection;

public class ListMaterialCommand extends AbstractQueryCommand implements Command {
    private String filter;
    private DAO<Material, Integer> dao;

    public ListMaterialCommand(DAO<Material, Integer> dao) {
        this.dao = dao;
    }
    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            filter = args[0];
        }
        else {
            filter = "";
        }
    }

    @Override
    public void execute() {
        Collection<Material> materials = null;
        if (null == filter || "".equals(filter)) {
            materials = dao.readAll();
        }
        else {
            materials = dao.find(filter);
        }
        for (Material material : materials) {
            System.out.println(String.format("%d\t%s", material.getId(), material.getName()));
        }
    }
}

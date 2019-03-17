package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.domain.hbm.Type;

import java.util.Collection;

public class ListTypeCommand extends AbstractQueryCommand implements Command {
    private String filter;
    private DAO<Type, Integer> dao;

    public ListTypeCommand(DAO<Type, Integer> dao) {
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
        Collection<Type> types = null;
        if (null == filter || "".equals(filter)) {
            types = dao.readAll();
        }
        else {
            types = dao.find(filter);
        }
        for (Type type : types) {
            System.out.println(String.format("%d\t%s", type.getId(), type.getName()));
        }
    }
}

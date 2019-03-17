package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.domain.hbm.Manufacture;

import java.util.Collection;

public class ListManufactureCommand extends AbstractQueryCommand implements Command {
    private String filter;
    private DAO<Manufacture, Integer> dao;

    public ListManufactureCommand(DAO<Manufacture, Integer> dao) {
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
        Collection<Manufacture> Manufactures = null;
        if (null == filter || "".equals(filter)) {
            Manufactures = dao.readAll();
        }
        else {
            Manufactures = dao.find(filter);
        }
        for (Manufacture Manufacture : Manufactures) {
            System.out.println(String.format("%d\t%s", Manufacture.getId(), Manufacture.getName()));
        }
    }
}
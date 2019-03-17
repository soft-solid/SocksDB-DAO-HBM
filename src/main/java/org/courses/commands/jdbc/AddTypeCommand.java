package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.Type;

import java.util.Arrays;

public class AddTypeCommand implements Command {
    private String typeName;
    private DAO<Type, Integer> dao;

    public AddTypeCommand(DAO<Type, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            typeName = args[0];
        }
        else {
            throw new CommandFormatException("Type name is not specified");
        }
    }

    @Override
    public void execute() {
        Type t = new Type();
        t.setName(typeName);
        dao.save(Arrays.asList(t));
    }
}
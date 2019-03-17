package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.Type;

import java.util.ArrayList;

public class AddTypeCommand implements Command {
    private String[] typeNames;
    private DAO<Type, Integer> dao;

    public AddTypeCommand(DAO<Type, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            typeNames = args;
        }
        else {
            throw new CommandFormatException("Type name is not specified");
        }
    }

    @Override
    public void execute() {
        ArrayList<Type> collection = new ArrayList<Type>(typeNames.length);
        Type t = null;
        for (String typeName : typeNames)
        {
            t = new Type();
            t.setName(typeName);
            collection.add(t);
        }
        dao.save(collection);
    }
}
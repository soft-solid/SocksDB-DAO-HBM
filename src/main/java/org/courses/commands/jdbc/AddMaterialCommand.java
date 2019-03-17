package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.Material;

import java.util.ArrayList;

public class AddMaterialCommand extends AbstractQueryCommand implements Command {
    private String[] MaterialNames;
    private DAO<Material, Integer> dao;

    public AddMaterialCommand(DAO<Material, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            MaterialNames = args;
        }
        else {
            throw new CommandFormatException("Material name is not specified");
        }
    }

    @Override
    public void execute() {
        ArrayList<Material> collection = new ArrayList<Material>(MaterialNames.length);
        Material m = null;
        for (String MaterialName : MaterialNames)
        {
            m = new Material();
            m.setName(MaterialName);
            collection.add(m);
        }
        dao.save(collection);
    }
}

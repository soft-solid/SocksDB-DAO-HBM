package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.Material;
import org.courses.domain.hbm.Type;
import org.hibernate.Session;

import java.util.Arrays;

public class AddMaterialCommand extends AbstractQueryCommand implements Command {
    private String materialName;
    private DAO<Material, Integer> dao;

    public AddMaterialCommand(DAO<Material, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            materialName = args[0];
        }
        else {
            throw new CommandFormatException("Material name is not specified");
        }
    }

    @Override
    public void execute() {
        Material m = new Material();
        m.setName(materialName);
        dao.save(Arrays.asList(m));
    }
}

package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.Manufacture;

import java.util.ArrayList;

public class AddManufactureCommand extends AbstractQueryCommand implements Command {
    private String[] ManufactureNames;
    private DAO<Manufacture, Integer> dao;

    public AddManufactureCommand(DAO<Manufacture, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            ManufactureNames = args;
        } else {
            throw new CommandFormatException("Manufacture name is not specified");
        }
    }

    @Override
    public void execute() {
        ArrayList<Manufacture> collection = new ArrayList<Manufacture>(ManufactureNames.length);
        Manufacture m = null;
        for (String ManufactureName : ManufactureNames) {
            m = new Manufacture();
            m.setName(ManufactureName);
            collection.add(m);
        }
        dao.save(collection);
    }
}

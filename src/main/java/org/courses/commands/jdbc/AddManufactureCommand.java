package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.Manufacture;

import java.util.Arrays;

public class AddManufactureCommand extends AbstractQueryCommand implements Command {
    private String manufactureName;
    private DAO<Manufacture, Integer> dao;

    public AddManufactureCommand(DAO<Manufacture, Integer> dao) {
        this.dao = dao;
    }
    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            manufactureName = args[0];
        }
        else {
            throw new CommandFormatException("Manufacture name is not specified");
        }
    }

    @Override
    public void execute() {
        Manufacture m = new Manufacture();
        m.setName(manufactureName);
        dao.save(Arrays.asList(m));
    }
}

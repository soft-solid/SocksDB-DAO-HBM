package org.courses.commands.jdbc;

import org.courses.commands.Command;
import org.apache.commons.validator.routines.IntegerValidator;
import org.courses.DAO.DAO;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.Manufacture;

import java.util.ArrayList;

public class DeleteManufactureCommand implements Command {

    private int[] ManufactureIDs;
    private DAO<Manufacture, Integer> dao;

    private IntegerValidator Int32 = IntegerValidator.getInstance();

    public DeleteManufactureCommand(DAO<Manufacture, Integer> dao) {
        this.dao = dao;
    }
    @Override
    public void parse(String[] args) {
        int lengthArgs = args.length;
        if (lengthArgs > 0) {
            ManufactureIDs = new int[lengthArgs];
            for (int i = 0; i < lengthArgs; i++) {
                try {
                    ManufactureIDs[i] = Int32.validate(args[i]);
                }
                catch(Exception e) {
                    throw new CommandFormatException("Illegal format parameters");
                }
            }
        }
        else {
            throw new CommandFormatException("Manufacture id is not specified");
        }
    }

    @Override
    public void execute() {
        ArrayList<Manufacture> collection = new ArrayList<Manufacture>(ManufactureIDs.length);
        Manufacture m = null;
        for (int typeID : ManufactureIDs)
        {
            m = new Manufacture();
            m.setId(typeID);
            collection.add(m);
        }
        dao.delete(collection);
    }
}

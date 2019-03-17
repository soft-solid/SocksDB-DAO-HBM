package org.courses.commands.jdbc;

import org.apache.commons.validator.routines.IntegerValidator;
import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.Type;

import java.util.ArrayList;

public class DeleteTypeCommand implements Command {

    private int[] typeIDs;
    private DAO<Type, Integer> dao;

    private IntegerValidator Int32 = IntegerValidator.getInstance();

    public DeleteTypeCommand(DAO<Type, Integer> dao) {
        this.dao = dao;
    }
    @Override
    public void parse(String[] args) {
        int lengthArgs = args.length;
        if (lengthArgs > 0) {
            typeIDs = new int[lengthArgs];
            for (int i = 0; i < lengthArgs; i++) {
                try {
                    typeIDs[i] = Int32.validate(args[i]);
                }
                catch(Exception e) {
                    throw new CommandFormatException("Illegal format parameters");
                }
            }
        }
        else {
            throw new CommandFormatException("Type id is not specified");
        }
    }

    @Override
    public void execute() {
        ArrayList<Type> collection = new ArrayList<Type>(typeIDs.length);
        Type t = null;
        for (int typeID : typeIDs)
        {
            t = new Type();
            t.setId(typeID);
            collection.add(t);
        }
        dao.delete(collection);
    }
}

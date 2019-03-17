package org.courses.commands.jdbc;

import org.apache.commons.validator.routines.IntegerValidator;
import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.Material;

import java.util.ArrayList;

public class DeleteMaterialCommand implements Command {

    private int[] MaterialIDs;
    private DAO<Material, Integer> dao;

    private IntegerValidator Int32 = IntegerValidator.getInstance();

    public DeleteMaterialCommand(DAO<Material, Integer> dao) {
        this.dao = dao;
    }
    @Override
    public void parse(String[] args) {
        int lengthArgs = args.length;
        if (lengthArgs > 0) {
            MaterialIDs = new int[lengthArgs];
            for (int i = 0; i < lengthArgs; i++) {
                try {
                    MaterialIDs[i] = Int32.validate(args[i]);
                }
                catch(Exception e) {
                    throw new CommandFormatException("Illegal format parameters");
                }
            }
        }
        else {
            throw new CommandFormatException("Material id is not specified");
        }
    }

    @Override
    public void execute() {
        ArrayList<Material> collection = new ArrayList<Material>(MaterialIDs.length);
        Material m = null;
        for (int typeID : MaterialIDs)
        {
            m = new Material();
            m.setId(typeID);
            collection.add(m);
        }
        dao.delete(collection);
    }
}

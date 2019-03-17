package org.courses;

import org.courses.DAO.DAO;
import org.courses.DAO.hbm.ManufactureDao;
import org.courses.DAO.hbm.MaterialDao;
import org.courses.DAO.hbm.TypeDao;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.commands.jdbc.*;
import org.apache.tools.ant.types.Commandline;
import org.courses.domain.hbm.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    static SessionFactory getSessionFactory() {
        return new Configuration()
                //.configure("/hbm/hibernate.cfg.xml")
                .setProperty("connection.driver_class", "org.sqlite.JDBC")
                .setProperty("dialect", "org.hibernate.dialect.SQLiteDialect")
                .setProperty("connection.pool_size", "1")
                .setProperty("show_sql", "true")
                .setProperty("format_sql", "true")
                .setProperty("hibernate.jdbc.batch_size", "30")
                .setProperty("hibernate.connection.url", "jdbc:sqlite:D:\\1dotNet\\Projects\\JdbcConcole DAO HBM\\CourseDB.db")
                .addAnnotatedClass(Manufacture.class)
                .addAnnotatedClass(Material.class)
                .addAnnotatedClass(Type.class)
                .addAnnotatedClass(Socks.class)
                .addAnnotatedClass(Composition.class)
                .addAnnotatedClass(Storage.class)
                .buildSessionFactory();
    }

    static Map<String, Command> commands;

    static {
        SessionFactory factory = getSessionFactory();
        DAO<Type, Integer> typeDao = new TypeDao(factory);
        DAO<Material, Integer> materialDao = new MaterialDao(factory);
        DAO<Manufacture, Integer> manufactureDao = new ManufactureDao(factory);


        commands = new HashMap<>();
        commands.put("connect", new CreateDb());
        commands.put("table", new CreateTable());
        commands.put("addtype", new AddTypeCommand(typeDao));
        commands.put("addmaterial", new AddMaterialCommand(materialDao));
        commands.put("addmanufacture", new AddManufactureCommand(manufactureDao));
        commands.put("listmaterial", new ListMaterialCommand(materialDao));
        commands.put("listtype", new ListTypeCommand(typeDao));
        commands.put("listmanufacture", new ListManufactureCommand(manufactureDao));
        commands.put("deletematerial", new DeleteMaterialCommand(materialDao));
        commands.put("deletetype", new DeleteTypeCommand(typeDao));
        commands.put("deletemanufacture", new DeleteManufactureCommand(manufactureDao));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greetUser();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            parseUserInput(line);
            greetUser();
        }
    }

    private static void parseUserInput(String input) {
        String userCommand[] = Commandline.translateCommandline(input);

        String commandName = userCommand[0];
        String[] params = new String[userCommand.length - 1];
        System.arraycopy(userCommand, 1, params, 0, params.length);

        try {
            executeCommand(commandName, params);
        }
        catch (CommandFormatException e) {
            System.out.println(String.format("%s has some invalid arguments", commandName));
        }
        catch (NotImplementedException e) {
            System.out.println(String.format("Unknown command %s", commandName));
        }
    }

    private static void executeCommand(String commandName, String[] params) {
        Command command = commands.get(commandName);

        if (null == command)
            throw new NotImplementedException();

        command.parse(params);
        command.execute();
    }

    private static void greetUser() {
        System.out.print("courses-jdbc>");
    }
}

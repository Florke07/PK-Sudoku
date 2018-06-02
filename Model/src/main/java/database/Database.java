package database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class Database {
    Connection connection;
    Statement statement;
    public String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String framework = "embedded";
    private String protocol = "jdbc:derby:";
    private String dbName = "DB";
    final Logger logger = LoggerFactory.getLogger(Database.class);

    public void connect() {
        //String url = " jdbc:derby:DB;create=true"; //In-memory, w pamięci a nie w plikach
        //String url = "jdbc:derby:c:\\Apache\\DBs\\demo;create=true"; //File-based, w plikach
        try {
            connection = DriverManager.getConnection(protocol + dbName + ";create=true");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
           logger.info("Connected to and created database " + dbName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void createTableUsers() {
        try {
            statement.executeUpdate("CREATE table users (id int primary key, name varchar(50))");
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void createTableBoard() {

        try {
            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet rs = dmd.getTables(null, "APP", "BOARDS", null);
            if (!rs.next()) {
                statement.executeUpdate("create table boards (id int primary key, name varchar(50))");
                connection.commit();
                logger.info("Created table boards");
            } else {
                logger.info("Table boards already exists");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addToUsers() {
        try {
            statement.executeUpdate("insert into users values (1,'jan')");
            statement.executeUpdate("insert into users values (2,'kowalski')");
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void selectUsers() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            connection.commit();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "     Name: " + resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void selectBoards() {
        try {
            ResultSet rs = statement.executeQuery("select * from boards");
            connection.commit();
            while (rs.next()) {
                System.out.println("ID: "+rs.getInt("id")+" Name: "+rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteDB() {
        try {
            statement.execute("drop table users");
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (framework.equals("embedded"))
        {
            try
            {
                DriverManager.getConnection("jdbc:derby:;shutdown=true");
            }
            catch (SQLException se)
            {
                if (( (se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState()) ))) {
                   logger.info("Derby shut down normally");
                } else {
                    logger.info("Derby did not shut down normally");

                }
            }
        }
    }
}

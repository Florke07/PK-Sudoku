package database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.SudokuBoard;
import sudokupart.SudokuColumn;
import sudokupart.SudokuField;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    Connection connection;
    Statement statement;
    private String framework = "embedded";
    private String protocol = "jdbc:derby:";
    private String dbName = "DB";
    final Logger logger = LoggerFactory.getLogger(Database.class);
    private int nb=0;

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

    private void insertField(SudokuField sf) {

    }

    private void insertColumn(SudokuColumn sc, String bName) {

    }

    public void insertBoard(SudokuBoard sb) {
        ArrayList<SudokuColumn> columns = new ArrayList<>();
        String nm = "Board"+Integer.toString(nb);
        for (int i=0;i<9;i++) {
            columns.add(sb.getColumn(i));
            insertColumn(columns.get(i), nm);
        }
        nb++;
    }

    public void dropTables() {
        try {
            statement.executeUpdate("drop table boards");
            statement.executeUpdate("drop table columns");
            statement.executeUpdate("drop table fields");
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void createTables() {
        createTableFields();
        createTableColumns();
        createTableBoards();
    }

    private void createTableBoards() {
        try {
            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet rs = dmd.getTables(null, "APP", "BOARDS", null);
            if (!rs.next()) {
                statement.executeUpdate("create table boards (" +
                        "id int primary key," +
                        "name varchar(50)," +
                        "col0 int," +
                        "col1 int," +
                        "col2 int," +
                        "col3 int," +
                        "col4 int," +
                        "col5 int," +
                        "col6 int," +
                        "col7 int," +
                        "col8 int," +
                        "constraint c0 foreign key (col0) references columns(id)," +
                        "constraint c1 foreign key (col1) references columns(id)," +
                        "constraint c2 foreign key (col2) references columns(id)," +
                        "constraint c3 foreign key (col3) references columns(id)," +
                        "constraint c4 foreign key (col4) references columns(id)," +
                        "constraint c5 foreign key (col5) references columns(id)," +
                        "constraint c6 foreign key (col6) references columns(id)," +
                        "constraint c7 foreign key (col7) references columns(id)," +
                        "constraint c8 foreign key (col8) references columns(id)" +
                        ")");
                connection.commit();
                logger.info("Created table boards");
            } else {
                logger.info("Table boards already exists");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createTableColumns() {
        try {
            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet rs = dmd.getTables(null, "APP", "COLUMNS", null);
            if (!rs.next()) {
                statement.executeUpdate("create table columns (" +
                        "id int primary key," +
                        "boardName varchar(50)," +
                        "filed0 int," +
                        "filed1 int," +
                        "filed2 int," +
                        "filed3 int," +
                        "filed4 int," +
                        "filed5 int," +
                        "filed6 int," +
                        "filed7 int," +
                        "filed8 int," +
                        "constraint f0 foreign key (filed0) references fields(id)," +
                        "constraint f1 foreign key (filed1) references fields(id)," +
                        "constraint f2 foreign key (filed2) references fields(id)," +
                        "constraint f3 foreign key (filed3) references fields(id)," +
                        "constraint f4 foreign key (filed4) references fields(id)," +
                        "constraint f5 foreign key (filed5) references fields(id)," +
                        "constraint f6 foreign key (filed6) references fields(id)," +
                        "constraint f7 foreign key (filed7) references fields(id)," +
                        "constraint f8 foreign key (filed8) references fields(id))");
                connection.commit();
                logger.info("Created table columns");
            } else {
                logger.info("Table columns already exists");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createTableFields() {
        try {
            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet rs = dmd.getTables(null, "APP", "FIELDS", null);
            if (!rs.next()) {
                statement.executeUpdate("create table fields (" +
                        "id int generated by default as identity (start with 1, increment by 1) primary key not null," +
                        "numer int," +
                        "modify varchar(10)" +
                        ")");
                connection.commit();
                logger.info("Created table fields");
            } else {
                logger.info("Table fields already exists");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   /* public void addToUsers() {
        try {
            statement.executeUpdate("insert into users values (1,'jan')");
            statement.executeUpdate("insert into users values (2,'kowalski')");
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }*/


   /* public void selectUsers() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            connection.commit();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "     Name: " + resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }*/

    public void deleteDB() {
        try {
            dropTables();
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

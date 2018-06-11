package database;

import com.sun.org.apache.xpath.internal.operations.Bool;
import exceptions.WrongValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.SudokuBoard;
import sudokupart.SudokuColumn;
import sudokupart.SudokuField;

import java.io.Serializable;
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
    private int tmpID=0;
    private int numerator=0;

    public void connect() {
        try {
            connection = DriverManager.getConnection(protocol + dbName + ";create=true");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            logger.info("Connected to and created database " + dbName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

        public void insertBoard(SudokuBoard sb) {
        String nm = "Board"+Integer.toString(numerator);
        try {
            String sql = "insert into FIELDS values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i=0;i<9;i++) {
                for (int j = 0; j < 9; j++) {
                    ps.setInt(1, numerator);
                    ps.setInt(2, sb.getValue(i, j));
                    ps.setString(3, nm);
                    ps.setString(4, Boolean.toString(sb.getField(i, j).isModifiable()));

                    ps.executeUpdate();
                    connection.commit();
                    numerator++;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public void dropTables() {
        try {
            statement.executeUpdate("drop table fields");
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void createTables() {
        createTableFields();

    }

    private void createTableFields() {
        try {
            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet rs = dmd.getTables(null, "APP", "FIELDS", null);
            if (!rs.next()) {
                statement.executeUpdate("create table fields (" +
                        "id int primary key," +
                        "numer int," +
                        "bname varchar(50)," +
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


    public SudokuBoard read(String name) {
        SudokuBoard nsb = new SudokuBoard();
        nsb.fillBoard();

        ResultSet rs2 = null;
        try {
            rs2 = statement.executeQuery("SELECT * FROM FIELDS");
            int i=0,j=0;

            while (rs2.next()) {
                Boolean bol = false;
                if (rs2.getString("MODIFY").equals("true")) bol=true;
                nsb.setValue(j,i,rs2.getInt(2), bol);
                if (i == 9) break;
                if (j == 8) {
                    i++;
                    j=0;
                } else {
                    j++;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nsb;
    }
    public void shutdownDB() {

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

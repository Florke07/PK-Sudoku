package database;

import exceptions.WrongValueException;
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

    private int insertField(SudokuField sf) {
        ResultSet rs=null;
        int tm=0;
        try {
            String sql = "insert into FIELDS values (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, tmpID);
            ps.setInt(2, sf.getFieldValue());
            ps.setString(3, Boolean.toString(sf.isModifiable()));
            ps.executeUpdate();
            connection.commit();
            tm=tmpID;
            tmpID++;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tm;
    }

    private int insertColumn(SudokuColumn sc, String bName) {
        ArrayList<Integer> rsFields = new ArrayList<>(9);
        int tm =0;
        for (int i=0;i<9;i++) {
            rsFields.add(insertField(sc.elements.get(i)));
        }
        try {
            String sql = "insert into COLUMNS values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pss = connection.prepareStatement(sql);
            pss.setInt(1, nb);
            pss.setString(2, bName);
            pss.setInt(3, rsFields.get(0));
            pss.setInt(4, rsFields.get(1));
            pss.setInt(5, rsFields.get(2));
            pss.setInt(6, rsFields.get(3));
            pss.setInt(7, rsFields.get(4));
            pss.setInt(8, rsFields.get(5));
            pss.setInt(9, rsFields.get(6));
            pss.setInt(10, rsFields.get(7));
            pss.setInt(11, rsFields.get(8));
            pss.executeUpdate();
            connection.commit();
            tm=nb;
            nb++;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tm;
    }

    public void insertBoard(SudokuBoard sb) {
        ArrayList<SudokuColumn> columns = new ArrayList<>();
        ArrayList<Integer> rsy = new ArrayList<>();
        String nm = "Board"+Integer.toString(numerator);
        for (int i=0;i<9;i++) {
            columns.add(sb.getColumn(i));
            rsy.add(insertColumn(columns.get(i), nm));
        }
        try {
            String sql = "insert into BOARDS values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numerator);
            ps.setString(2, nm);
            ps.setInt(3, rsy.get(0));
            ps.setInt(4, rsy.get(1));
            ps.setInt(5, rsy.get(2));
            ps.setInt(6, rsy.get(3));
            ps.setInt(7, rsy.get(4));
            ps.setInt(8, rsy.get(5));
            ps.setInt(9, rsy.get(6));
            ps.setInt(10, rsy.get(7));
            ps.setInt(11, rsy.get(8));
            ps.executeUpdate();
            connection.commit();
            numerator++;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


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
                        "id int primary key," +
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

    public SudokuBoard read(String name) {
        ResultSet rs = null;
        ArrayList<Integer> columns = new ArrayList<>();
        ArrayList<Integer> fields = new ArrayList<>();
        try {
            Statement ps = connection.createStatement();
            rs = ps.executeQuery("select * from BOARDS where NAME = '" + name +"'");
            connection.commit();
            for (int i=0;i<9;i++) {
                columns.add(rs.getInt(3));
            }

            //String sql2 = "selct * from COLUMNS where ID = '?'";
            Statement ps2 = connection.createStatement();

            for (int j=0;j<9;j++) {
                //ps2.setInt(1, columns.get(j));
                rs = ps2.executeQuery("selct * from COLUMNS where ID = '"+columns.get(j)+"'");
                connection.commit();
                fields.add(rs.getInt("FIELD0"));
                fields.add(rs.getInt("FIELD1"));
                fields.add(rs.getInt("FIELD2"));
                fields.add(rs.getInt("FIELD3"));
                fields.add(rs.getInt("FIELD4"));
                fields.add(rs.getInt("FIELD5"));
                fields.add(rs.getInt("FIELD6"));
                fields.add(rs.getInt("FIELD7"));
                fields.add(rs.getInt("FIELD8"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ResultSet nresult = null;

        SudokuBoard nsb = new SudokuBoard();
        nsb.fillBoard();
        int k=0;
        for (int i=0;i<9;i++) {
            for (int j=0;j<9;j++) {
                try {
                    //String s1 = "select NUMER as NUMER from FIELDS where ID = '?'";
                    Statement p1 = connection.createStatement();
                    //p1.setInt(1, fields.get(i));
                    nresult = p1.executeQuery("select NUMER as NUMER from FIELDS where ID = '"+fields.get(i)+"'");
                    nsb.setValue(i, j, nresult.getInt("NUMER"));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (WrongValueException w) {
                    w.printStackTrace();
                }

            }

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

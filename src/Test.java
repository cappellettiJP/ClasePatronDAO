

import java.sql.*;

public class Test {

    private static final String SQL_CREATE_TABLE = "DROP TABLE IF EXISTS PACIENTE; CREATE TABLE PACIENTE "
                                                    + "("
                                                    + " ID INT PRIMARY KEY,"
                                                    + " NOMBRE varchar(100) NOT NULL, "
                                                    + " EDAD INT NOT NULL,"
                                                    + " PASSWORD varchar(100) NOT NULL"
                                                    + " )";

    private static final String SQL_INSERT =  "INSERT INTO PACIENTE (ID, NOMBRE, EDAD, PASSWORD) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE =  "UPDATE PACIENTE SET PASSWORD=? WHERE ID=?";

    public static void main(String[] args) throws Exception {
        Paciente paciente = new Paciente("Jason", 24, "abcd");

        Connection connection = null;

        try {
            connection= getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLE);

            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT);
            psInsert.setInt(1, 1);
            psInsert.setString(2, paciente.getNombre());
            psInsert.setInt(3, paciente.getEdad());
            psInsert.setString(4, paciente.getPassword());

            psInsert.execute();

            connection.setAutoCommit(false);

            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1,"1234");
            psUpdate.setInt(2, 1);
            psUpdate.execute();
            int a = 4 /0;
            connection.commit();

            connection.setAutoCommit(true);

            String sql = "SELECT * FROM PACIENTE";
            Statement sqlSmt = connection.createStatement();
            ResultSet rs = sqlSmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + rs.getString(2) + rs.getInt(3) + rs.getString(4));
            }


        } catch (Exception e) {
            connection.rollback();
        } finally {
            connection.close();
        }

        Connection connection2= getConnection();
        String sql = "SELECT * FROM PACIENTE";
        Statement sqlSmt = connection2.createStatement();
        ResultSet rs = sqlSmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + rs.getString(2) + rs.getInt(3) + rs.getString(4));
        }

    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/test", "sa","");


    }

}

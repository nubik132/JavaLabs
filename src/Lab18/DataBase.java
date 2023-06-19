package Lab18;

import java.sql.*;

public class DataBase {
    private final String URL = "jdbc:mysql://localhost:3306/shippingcompany";
    private final String USER = "vald";
    private final String PASSWORD = "DBpass";
    private static DataBase instance;
    private DbConnection conn;

    public DataBase() {}

    private void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = new DbConnection();
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setConn(connection);
            Statement statement = connection.createStatement();
            conn.setStatement(statement);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Подключение к базе данных выполнилось с ошибкой");
        }
    }
    public DbConnection getConnection(){
        return conn;
    }
    public Statement getStatement(){
        return conn.getStatement();
    }
    public ResultSet getResultSet(){
        return conn.getResultSet();
    }
    public DataBase getInstance(){
        if (instance == null){
            instance = new DataBase();
            instance.connect();
        }
        return instance;
    }
}

package Lab11;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    private static Statement statement;
    private static ResultSet resultSet;
    private  static Connection conn;

    public static void main(String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shippingcompany", "vald", "DBpass");
            System.out.println("Connected to database!");

            statement = conn.createStatement();
            scanner = new Scanner(System.in);

            startCUI();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    static void startCUI(){
        while (true){
            System.out.println("Введите имя таблицы");
            showTables();
            System.out.println("Введите номер команды");
            System.out.println("""
                    1. INSERT
                    2. UPDATE
                    3. DELETE
                    4. Показать таблицу""");

            execute(scanner.next(), scanner.nextInt());
        }
    }

    static void showTables(){
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            String[] types = {"TABLE"};
            resultSet = metaData.getTables("shippingcompany", null, null, types);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("TABLE_NAME"));
            }
        }
        catch (SQLException ex){

        }
    }

    static void execute(String tableName, int commandNumber){
    try {
        switch (commandNumber) {
            case (1) -> {
                System.out.println("Введите параметр(ы)");
                String parameters = scanner.nextLine();
                statement.executeUpdate("INSERT INTO " + tableName +
                        " VALUES (" + parameters + ");");
            }
            case (2) -> {
                System.out.println("Введите параметр(ы)");
                String parameters = scanner.nextLine();
                statement.executeUpdate("UPDATE " + tableName +
                        " SET " + parameters + ";");
            }

            case (3) -> {
                System.out.println("Введите параметр(ы)");
                String parameters = scanner.nextLine();
                statement.executeUpdate("DELETE FROM " + tableName + " " + parameters + ";");

            }
            case (4) -> select(tableName);
            default -> System.out.println("Что-то не то введено");
            }
        }

        catch (SQLException ex){
            System.out.println("Ошибка в execute: " + ex.getMessage());

            }
        }

    static void select(String table){
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + table + ";");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 0; i < columnCount; i++) {
                   System.out.println(metaData.getColumnName(i + 1) + ": " + resultSet.getObject(metaData.getColumnName(i + 1)));
                }
                System.out.println("==========================");
            }
        }
        catch (SQLException ex){
            System.out.println("Ошибка в select");
        }
    }
}

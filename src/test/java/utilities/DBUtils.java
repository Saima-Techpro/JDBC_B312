package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    // Create class variables. Their scope is wider
    public static Connection connection;

    public static Statement statement;
    public static ResultSet resultSet;

//    Step 1: Register with Driver class (OPTIONAL) because it's not needed anymore (since JAVA 7)
//    Step 2: Create connection with the database

    public static Connection connectToDataBase(){
        String url = "jdbc:postgresql://localhost:5433/jdbc_b312";
        String userName = "b312_user";
        String password = "password";

        try {
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection is successful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;

    }

//    Step 3: Create statement
    public static Statement createStatement(){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }


//    Step 4: Execute the query

    public static ResultSet executeQuery(String query){
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    // Method to get the data from a given column and return as a list

    public static List<Object> getColumnData(String columnName , String tableName){

        List<Object> list = new ArrayList<>(); // to store the data that we extract from DB

        String query = "SELECT "+columnName+" FROM "+tableName;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                list.add(resultSet.getObject(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return list;

    }




//    Step 5: Close the connection

    public static void closeConnection(){
        try {
            statement.close();
            connection.close();
            System.out.println("Connection closed successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}

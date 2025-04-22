import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {

//        Step 1: OPTIONAL (since JAVA 7)


//        Step 2: Create connection with the database
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/jdbc_b312", "b312_user", "password");
        if (connection != null) {
            System.out.println("Connected successfully!");
        } else {
            System.out.println("Not connected!");
        }


//        Step 3: Create statement
        Statement statement = connection.createStatement();

//        Step 4: Execute the query
        System.out.println( "============== Task 1 ==============");
        // Read all data from the table
        // SELECT * FROM employees
        ResultSet rs = statement.executeQuery("SELECT * FROM employees");
       /*
        System.out.println(rs1); //returns reference of the resultSet
        System.out.println(rs1.next()); // true
        System.out.println(rs1.getInt("employee_id")); // 1
        System.out.println(rs1.getString("employee_name")); // John Doe
        System.out.println(rs1.getInt(3)); // 1000
        System.out.println(rs1.next()); // false

        */

        // In order to avoid repetition, we use loop to check if the ResultSet hass data or not
        while (rs.next()){
            System.out.println(rs.getInt("employee_id"));
        }


        System.out.println("==============Task 2 ===============");
        // We created countries table from pgAdmin4 and added data as well

        // Get phone_code and country_name from the countries table where code is greater than 500
        // SELECT phone_code , country_name FROM countries WHERE phone_code > 500
        String query1 = "SELECT phone_code , country_name FROM countries WHERE phone_code > 500";

        ResultSet rs1 = statement.executeQuery(query1);

        while (rs1.next()){
            //System.out.println(rs1.getInt("phone_code")+ " , " + rs1.getString("country_name"));
            System.out.println(rs1.getInt(1)+ " , " + rs1.getString(2));
        }

        /*
        HW:
         // Create the following table using execute() OR executeQuery() method
        CREATE TABLE developers (id SERIAL PRIMARY KEY, name VARCHAR(50), salary REAL, prog_lang VARCHAR(20));
         */


//        Step 5: Close the connection
        if (connection != null){
            statement.close();
            connection.close();
            System.out.println("Disconnected successfully!");
        }else {
            System.out.println("Still connected!");
        }

    }
}

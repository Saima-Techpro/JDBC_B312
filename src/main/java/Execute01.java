import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /*
        NOTES:

        pgAdmin4 is used for
        1. Creating database, tabular structure, adding or deleting values etc.
        2. Manual Testing of DB by Devs as well as Testers

        pgAdmin4 is GUI (Graphic User Interface) for Postgres SQL Database.

        JDBC Driver is used for AUTOMATION of Database.

        To create connection with DB, we need to follow these steps:
        Step 1: Register with Driver class (OPTIONAL) because it's not needed anymore (since JAVA 7)
        Step 2: Create connection with the database
        Step 3: Create statement
        Step 4: Execute the query
        Step 5: Close the connection


         */

//        Step 1: Register with Driver class (OPTIONAL) because it's not needed anymore
        Class.forName("org.postgresql.Driver");

//        Step 2: Create connection with the database
//        PLEASE check the port number if you get an error => port: 5432 OR 5433
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/jdbc_b312", "b312_user", "password");

        if (connection != null){
            System.out.println("Connection is successful!");
        }else {
            System.out.println("Not connected!!");
        }


//        Step 3: Create statement
        Statement statement = connection.createStatement();
//        Step 4: Execute the query

//        boolean query1 = statement.execute("CREATE TABLE IF NOT EXISTS workers (worker_id INT, worker_name VARCHAR (30), salary INT)");

        boolean query1 = statement.execute("CREATE TABLE IF NOT EXISTS employees (employee_id INT, employee_name VARCHAR (30), salary INT)");
        System.out.println("query1 = " + query1); // false because execute() is working with DDL query here and there is no resultSet that's why execute() returns false


        // Add a column employee_address
        // ALTER TABLE employees ADD COLUMN IF NOT EXISTS employee_address VARCHAR (60)
        boolean query2 = statement.execute("ALTER TABLE employees ADD COLUMN IF NOT EXISTS employee_address VARCHAR (60)");
        System.out.println("query2 = " + query2); // false => still working DDL query

        // To see the table on the console
        // SELECT * FROM employees
        boolean query3 = statement.execute("SELECT * FROM employees");
        System.out.println("query3 = " + query3); // true because execute() works with a DQL query here which returns a resultSet

        // Add a record to employees
        // INSERT INTO employees VALUES (01, 'John Doe', 1000, 'Texas, US')
        boolean query4 = statement.execute("INSERT INTO employees VALUES (01, 'John Doe', 1000, 'Texas, US')");
        System.out.println("query4 = " + query4); // false => DML


        // Find the record where employee_id is 4
        // SELECT * FROM employees WHERE employee_id = 4
        boolean query5 =statement.execute("SELECT * FROM employees WHERE employee_id = 4");
        System.out.println("query5 = " + query5); // true

        // Drop this table
        // DROP TABLE employees
        boolean query6 = statement.execute("DROP TABLE employees");
        System.out.println("query6 = " + query6); // false => DDL query

         /*
        NOTES:
        1. execute() method returns boolean
        2. execute() method is used with DDL (CREATE, ALTER, DROP table), with DQL(reading the data using SELECT)
           and DML(INSERT INTO, UPDATE).
        3. With some queries, execute() method returns FALSE, with some other, it returns TRUE.

        SELECT Statements (DQL): These statements are meant to retrieve data from the database. When such a statement is
        executed, execute() returns true to indicate that a ResultSet has been produced.

        INSERT, UPDATE, DELETE Statements (DML): These statements modify the data in the database but do not
        produce a ResultSet. When such a statement is executed, execute() returns false.

        DDL Statements: These statements alter the schema of the database (e.g., CREATE TABLE, ALTER TABLE).
           These also do not produce a ResultSet, so execute() returns false.
         */



//        Step 5: Close the connection

        if (connection != null){
            statement.close();
            connection.close();
            System.out.println("Statement and Connection closed successfully!");
        }else {
            System.out.println("Connection is not closed!!");
        }




    }
}

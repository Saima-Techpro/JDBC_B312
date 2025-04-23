import java.sql.*;

public class ExecuteQuery02 {

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
        // HW
        // Create students table and add data from pgAdmin4

        System.out.println("======== Task 1 ==========");
        // Display names of the students and their grades if their grades are higher than the pass grade of their department
        String query1 = "SELECT name, grade FROM students s INNER JOIN departments d ON s.department = d.department WHERE s.grade > d.pass_grade";

        ResultSet rs1 = statement.executeQuery(query1);

        while (rs1.next()){
//            System.out.println(rs2.getObject(1) + " , " + rs2.getObject(2)); OR

//            System.out.println(rs2.getString(1) + " , " + rs2.getInt(2));  // precise data type + column index OR
            System.out.println(rs1.getString("name") + " , " + rs1.getInt("grade"));  // precise data type + column names
        }
        System.out.println("========= Task 2 =========");
        // Print department name and grade of department which has the second-highest pass_grade
        // 1st way: Using SUB-QUERY
        System.out.println("First way:");
        String query2 = "SELECT department, pass_grade FROM departments WHERE pass_grade IN (SELECT MAX(pass_grade) AS second_highest_pass_grade FROM departments WHERE pass_grade < (SELECT MAX(pass_grade) FROM departments));";
        ResultSet rs2 = statement.executeQuery(query2);
        while(rs2.next()){
            System.out.println(rs2.getString(1) + " , " +rs2.getInt(2));
        }

        // 2nd way: Using ORDER BY

        System.out.println("Second way:");

        String query3="SELECT department , pass_grade FROM departments ORDER BY  pass_grade DESC LIMIT 1 OFFSET 1";
        ResultSet rs3=statement.executeQuery(query3);

        while(rs3.next()){
            System.out.println(rs3.getString(1)+": "+ rs3.getInt(2));
        }


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

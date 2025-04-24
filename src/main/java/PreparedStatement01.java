import java.sql.*;

public class PreparedStatement01 {
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

        System.out.println("========== Task 1 ==============");
//        (use PreparedStatement)

//      Update pass_grade to 475 of Mathematics departments
//        String query1 = "UPDATE departments SET pass_grade = 475 WHERE department = 'Mathmatics'";
//      String query1 = "UPDATE departments SET pass_grade = 470 WHERE department = 'Management'";
//      String query1 = "UPDATE departments SET pass_grade = 480 WHERE department = 'Literature'";
//      String query1 = "UPDATE departments SET pass_grade = 490 WHERE department = 'Psychology'";
//      String query1 = "UPDATE departments SET pass_grade = 530 WHERE department ILIKE 'Comp.Eng.'";
//      String query1 = "UPDATE departments SET pass_grade = 530 WHERE department ILIKE 'comp.eng.'";

        // Note: ILIKE is used to ignore the lower/upper cases

        // To avoid repetition, we create a dynamic query (parameterised query)

        String query1 = "UPDATE departments SET pass_grade = ? WHERE department ILIKE ? "; // parameterised query

        PreparedStatement prs1 = connection.prepareStatement(query1);

        prs1.setInt(1, 470);
        prs1.setString(2, "Management");

        // Now we use executeUpdate()
        int rowsUpdated = prs1.executeUpdate();
        System.out.println("rowsUpdated = " + rowsUpdated); // 1


        // To see the table
        ResultSet rs1 = statement.executeQuery("SELECT * FROM departments");

        while (rs1.next()){
            System.out.println(rs1.getInt("pass_grade") +" - "+ rs1.getString("department"));
        }

        // To update pass_grade in Comp.Sc. department, we don't need to create a new query and preparedstatement

        prs1.setString(2, "Comp.Eng.");
        prs1.setInt(1, 530);

        System.out.println(prs1.executeUpdate()); // 1

        // To see the table
        ResultSet rs2 = statement.executeQuery("SELECT * FROM departments");

        while (rs2.next()){
            System.out.println(rs2.getInt("pass_grade") +" - "+ rs2.getString("department"));
        }

        System.out.println("========== Task 2 ==============");

        // Delete students that belong to 'Mathematics' dept. from students table

        String query2 = "DELETE FROM students WHERE department ILIKE ? ";
        PreparedStatement prs2 = connection.prepareStatement(query2);

        prs2.setString(1, "psychology");
        prs2.executeUpdate();

        // To see the table
        ResultSet rs3 = statement.executeQuery("SELECT * FROM students");

        while (rs3.next()){
            System.out.println(rs3.getInt("id") + " , " +
                    rs3.getString("name") + " , " +
                    rs3.getString("city") + " , " +
                    rs3.getInt("grade") + " , " +
                    rs3.getString("department"));
        }


        System.out.println("========== Task 3 ==============");
        // Insert software engineering department in departments table
        // INSERT INTO departments VALUES (5005, 'Software Eng.', 540, 'East')
        String query3 = "INSERT INTO departments VALUES (? , ?, ?, ?)";
        PreparedStatement prs3 = connection.prepareStatement(query3);

        prs3.setInt(1, 5005);
        prs3.setString(4, "West");
        prs3.setInt(3, 540);
        prs3.setString(2, "Software Eng.");

        prs3.executeUpdate();

        // To see the table
        ResultSet rs4 = statement.executeQuery("SELECT * FROM departments");

        while (rs4.next()){
            System.out.println(rs4.getString("department") + " - " + rs4.getInt("pass_grade"));
        }




        //        Step 5: Close the connection
        if (connection != null){
            prs1.close();
            prs2.close();
            prs3.close();
            statement.close();
            connection.close();
            System.out.println("Disconnected successfully!");
        }else {
            System.out.println("Still connected!");
        }








    }
}

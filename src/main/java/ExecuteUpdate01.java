import java.sql.*;

public class ExecuteUpdate01 {
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

        System.out.println("============== Task 1============");

//        Step 4: Execute the query
//        Update salaries of developers whose salaries are less than average salary with average salary

//        String query1 = "UPDATE developers SET salary = (SELECT AVG(salary) FROM developers) WHERE salary < (SELECT AVG(salary) FROM developers)";
//
//        int numOfRowsUpdated = statement.executeUpdate(query1);
//        System.out.println("numOfRowsUpdated = " + numOfRowsUpdated); //11


        // To see the updated data, we ALWAYS use executeQuery()
        String query2 = "SELECT * FROM developers";

        ResultSet rs2 = statement.executeQuery(query2);

        while(rs2.next()){
            System.out.println(rs2.getInt("id") + " - " +
                    rs2.getString("name") + " - " +
                    rs2.getInt("salary") + " - " +
                    rs2.getString("prog_lang"));
        }

        System.out.println("============== Task 2 ============");
        // Add a new developer to this table (id, name, salary, prog_lang)

//        String query3 = "INSERT INTO developers VALUES (25, 'James Bond', 19000, 'Python')";
//
//        int rowsUpdated = statement.executeUpdate(query3);
//        System.out.println("rowsUpdated = " + rowsUpdated); // 1

        // NOTE: we can reuse query2 => SELECT * FROM developers to see the new row is added or not
        // BUT WE MUST CREATE A NEW RESULTSET

        ResultSet rs3 = statement.executeQuery(query2);

        while(rs3.next()){
//            System.out.println(rs3.getInt("id") + " - " +
//                    rs3.getString("name") + " - " +
//                    rs3.getInt("salary") + " - " +
//                    rs3.getString("prog_lang"));
            int id = rs3.getInt("id");
            String name = rs3.getString("name");
            int salary = rs3.getInt("salary");
            String prog_lang = rs3.getString("prog_lang");

            System.out.println(id +" , "+ name +" , "+ salary +" , "+ prog_lang);
        }

        System.out.println("============== Task 3 ============");
        // Delete the rows where prog_lang  is 'Ruby'
        String query4 = "DELETE FROM developers WHERE prog_lang = 'Ruby'";

        statement.executeUpdate(query4); // we don't have to store in int variable

        // To see the data from the table
        ResultSet rs4 = statement.executeQuery(query2);

        while(rs4.next()){
            int id = rs4.getInt("id");
            String name = rs4.getString("name");
            int salary = rs4.getInt("salary");
            String prog_lang = rs4.getString("prog_lang");

            System.out.println(id +" , "+ name +" , "+ salary +" , "+ prog_lang);
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

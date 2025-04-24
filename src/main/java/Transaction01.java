import java.sql.*;

public class Transaction01 {
    public static void main(String[] args) throws SQLException {
        //        Step 1: OPTIONAL (since JAVA 7)
//        Step 2: Create connection with the database
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/jdbc_b312", "b312_user", "password");
        if (connection != null) {
            System.out.println("Connected successfully!");
        } else {
            System.out.println("Not connected!");
        }

        // con.setAutoCommit(true); This is the code that works at the background once connection with the DB is established
        // It allows the queries (transactions) to be executed (committed/shipped) AUTOMATICALLY


//        Step 3: Create statement
        Statement statement = connection.createStatement();

//        Step 4: Execute the query
        System.out.println("========== Task 1 ==============");
        // Delete students that belong to 'Mathematics' dept. from students table
        // normal query
        // String query2 = "DELETE FROM students WHERE department ILIKE 'Mathematics'";

        // parameterised query
        String query2 = "DELETE FROM students WHERE department ILIKE ? "; // Transaction
        PreparedStatement prs2 = connection.prepareStatement(query2);

        prs2.setString(1, "psychology");
        // by default, this is made available at the background when connection with database is established
        // it allows the transaction (SQL Queries) to commit automatically
        // con.setAutoCommit(true); // works like constructor in Java ... it runs at the background whether you see it or not
        // To stop auto-commit (shipping) of transactions, we need to EXPLICITLY declare this setAutoCommit() to FALSE

        connection.setAutoCommit(false); // all transactions are under our control from this point onwards (Just like ON/OFF Switch)

        // Let's suppose our transaction doesn't go ahead because of some system failure
        // In order to simulate / pretend our system failed, we create this if block which will act as if
        // system failed while running this class

        if (true){
           connection.rollback(); // rollback() method is used to UNDO the transaction
        }

        // We allow the shipping /commit at this point by turning the switch ON =>  con.setAutoCommit(true);
        connection.setAutoCommit(true);

        // the update will go ahead

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

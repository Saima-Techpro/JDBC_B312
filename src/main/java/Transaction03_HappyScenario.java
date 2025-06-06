import java.sql.*;

public class Transaction03_HappyScenario {
    public static void main(String[] args) throws SQLException {

        //        Step 1: OPTIONAL (since JAVA 7)
//        Step 2: Create connection with the database
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/jdbc_b312", "b312_user", "password");
        if (connection != null) {
            System.out.println("Connected successfully!");
        } else {
            System.out.println("Not connected!");
        }

        // We take control here and STOP auto-commit
        connection.setAutoCommit(false);

//        Step 3: Create statement
        Statement statement = connection.createStatement();

//        Step 4: Execute the query
        System.out.println("========== Task 1 ==============");
        // Transfer amount of 1000 from account_num 1234 to account_num 5678

        // Normal query
        // for Fred
        //String query = "UPDATE accounts SET amount = amount - 1000 WHERE account_num = 1234";


        // Normal query
        // for Barnie
        // String query = "UPDATE accounts SET amount = amount + 1000 WHERE account_num = 5678";


        // Instead of writing two separate queries, we can create one parametrised query and use it for both

        String query = "UPDATE accounts SET amount = amount + ? WHERE account_num = ?";
        PreparedStatement prs = connection.prepareStatement(query);
        // For Fred
        prs.setInt(1, -1000);
        prs.setInt(2, 1234);
        prs.executeUpdate();


        // Let's suppose system doesn't fail here (HAPPY SCENARIO)
        if (false){
            throw new RuntimeException();
        }

        // For Barnie
        prs.setInt(1, +1000);
        prs.setInt(2, 5678);
        prs.executeUpdate();


        connection.setAutoCommit(true); // Now we allow the transaction to happen
        System.out.println("Transaction is successful!");

        // Barnie never received money because system failed in the middle of transaction
        // This is NEGATIVE SCENARIO => BOTH customers will not be happy at all



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

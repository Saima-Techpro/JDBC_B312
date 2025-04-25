import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountriesTest {


    // This test case is written in GHERKIN Language

    /*
        Given (pre-condition)
          User connects to the database
        When (action)
          User sends the query to get the "phone code" from "countries" table
        Then (verification)
          Verify that the number of "phone code" greater than 300 is 13.
        And (last step)
          User closes the connection

     */


    @Test
    public void countriesTest() throws SQLException {

//        User connects to the database
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/jdbc_b312", "b312_user", "password");

        if (connection != null){
            System.out.println("Connection is successful!");
        }else {
            System.out.println("Not connected!!");
        }

        Statement statement = connection.createStatement();



//        User sends the query to get the "phone code" from "countries" table
        String query = "SELECT phone_code FROM countries";

        // Create an empty list to store data coming from the resultSet

        List<Integer> codeList = new ArrayList<>();

        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            // System.out.println(rs.getInt("phone_code"));

            codeList.add(rs.getInt("phone_code"));
        }
        System.out.println("codeList = " + codeList);
        System.out.println(codeList.size());


//        Verify that the number of "phone code" greater than 300 is 13.

        int counter = 0 ;

        for (Integer w: codeList){
            if (w > 300){
                counter++;
            }
        }
        System.out.println("Number of phone code greater than 300:"  + counter);

        Assertions.assertEquals(13, counter, "result is not the same!");


//        User closes the connection

        if (connection != null){
            statement.close();
            connection.close();
            System.out.println("Statement and Connection closed successfully!");
        }else {
            System.out.println("Connection is not closed!!");
        }
    }
}

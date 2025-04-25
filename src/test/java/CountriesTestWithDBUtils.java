import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utilities.DBUtils.statement;

public class CountriesTestWithDBUtils {
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
    public void test() throws SQLException {
//        User connects to the database
        DBUtils.connectToDataBase();
        DBUtils.createStatement();

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
        DBUtils.closeConnection();










    }

}

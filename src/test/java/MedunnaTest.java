import org.junit.jupiter.api.Test;
import utilities.Medunna_DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedunnaTest {

     /*
         Test Case:
         1. User connects to the DataBase
         2. User sends the query with room_number to get data
         3. User verifies that room information is saved correctly
         4. User closes the connection

         */

    @Test
    public void medunnaTest() throws SQLException {

//        1. User connects to the DataBase
        Medunna_DBUtils.connectToDataBase();
        Medunna_DBUtils.createStatement();

//        2. User sends the query with room_number to get data
        String query = "SELECT * FROM room WHERE room_number = 648990;";
        ResultSet rs = Medunna_DBUtils.executeQuery(query);


//        3. User verifies that room information is saved correctly
        rs.next();
        int actualId = rs.getInt("id");
        //System.out.println("actualId = " + actualId);

        int actualRoomNumber = rs.getInt("room_number");
        //System.out.println("actualRoomNumber = " + actualRoomNumber);

        String actualRoomType = rs.getString("room_type");
        //System.out.println("actualRoomType = " + actualRoomType);

        boolean actualStatus = rs.getBoolean("status");
       // System.out.println("actualStatus = " + actualStatus);

        int actualPrice = rs.getInt("price");
        //System.out.println("actualPrice = " + actualPrice);

        String actualDescription = rs.getString("description");
        //System.out.println("actualDescription = " + actualDescription);

        // Set the expected data (that we sent from the UI side) so that we can use it for assertion/verification
        int expectedRoomNumber = 648990;
        String expectedRoomType = "PREMIUM_DELUXE";
        boolean expectedStatus = true;
        int expectedPrice = 1500;
        String expectedDescription = "Beach front";

        // Assertions
        assertEquals(expectedRoomNumber, actualRoomNumber);
        assertEquals(expectedRoomType, actualRoomType);
        assertEquals(expectedPrice, actualPrice);
        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedDescription, actualDescription);


        // How to use getColumnData() from Utils class
        List<Object> objectList = Medunna_DBUtils.getColumnData("first_name", "patient");
        System.out.println("objectList = " + objectList);


        // See if 'Gokhan' exists in our patient list

        boolean isFound = false;

        for (Object w : objectList){
            String name = (String) w;
            if (name.contains("Gokhan")){
                isFound = true;
                System.out.println( name + " is found");
            }
        }

        assertTrue(isFound, "Gokhan is not found in the list of the patients");


//        4. User closes the connection

        Medunna_DBUtils.closeConnection();

    }
}

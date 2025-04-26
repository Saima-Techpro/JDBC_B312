import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.Medunna_DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedunnaTest_WithPreparedSt {

     /*
         Test Case:
         1. User connects to the DataBase
         2. User sends the query with room_number to get data
         3. Verify that the room id is unique
         4. User closes the connection

         */

    @Test
    public void medunnaTest() throws SQLException {

//        1. User connects to the DataBase
        Medunna_DBUtils.connectToDataBase();
        Medunna_DBUtils.createStatement();

//        2. User sends the query with room_number to get data
        // Parameterised query
        String query = "SELECT * FROM room WHERE room_number = ? ;";

        PreparedStatement prs = Medunna_DBUtils.connection.prepareStatement(query);
        prs.setInt(1, 648990);

        ResultSet rs = prs.executeQuery();

//        3. Verify that the room id is unique
        // 1st way:
        // We can transfer data from the ResultSet into Java List
        List<Integer> roomId = new ArrayList<>();

        while (rs.next()){
            roomId.add(rs.getInt("id"));
        }

        System.out.println("room id = " + roomId); // [127496]

        // 2nd way:
        // We can transfer data from the ResultSet into int variable
//        rs.next();
//        int actualId = rs.getInt("id");
//        System.out.println("actualId = " + actualId);


        // roomId = [127496]
        int counter = 0;
        for (Integer w: roomId) {
            if (w > 1) {
                counter++;
            }
        }
        System.out.println("counter = " + counter);

        assertEquals(1,counter, "room id is not unique!");


//        4. User closes the connection
        prs.close();
        Medunna_DBUtils.closeConnection();

    }
}

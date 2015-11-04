package DA;

import PD.Message;

import java.sql.SQLException;

/**
 * Created by mingtao on 15-11-4.
 */
public class MessageDA {
    static public void add(Message message) {
        String sql = "INSERT INTO message(Content,Time,User)" +
                "VALUES ('" + message.getContent() + "','" +
                message.getTime() + "','" + message.getUserID() + "')";
        try {
            Base.statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    static public void delete(String ID) {
        String sql = "DELETE FROM message WHERE Id='" + ID + "'";
        try {
            Base.statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
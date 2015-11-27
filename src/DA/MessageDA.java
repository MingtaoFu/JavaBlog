package DA;

import PD.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mingtao on 15-11-4.
 */
public class MessageDA {
    static public int add(Message message) {
        String sql = "INSERT INTO Message(Content,User)" +
                "VALUES ('" + message.getContent() + "','" + message.getUserId() + "')";
        try {
            Base.statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return 2;
        }
        return 1;
    }

    static public int delete(int Id) {
        String sql1="SELECT * FROM Message WHERE Id='"+Id+"'";
        try {
            ResultSet resultSet=Base.statement.executeQuery(sql1);
            if(!resultSet.next()) {
                resultSet.close();
                return 5;
            }
            resultSet.close();
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return 2;
        }
        String sql = "DELETE FROM Message WHERE Id='" + Id + "'";
        try {
            Base.statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return 2;
        }
        return 1;
    }
    static public ArrayList<Message> find(){
        String sql="SELECT * FROM Message";
        Message message;
        ArrayList<Message> messages=new ArrayList<Message>();
        try {
            ResultSet resultSet = Base.statement.executeQuery(sql);
            if (!resultSet.next()) {
                messages=null;
                resultSet.close();
            }
            else {
                do {
                    message = new Message(resultSet.getString(2), resultSet.getString(4));
                    message.setId(resultSet.getInt(1));
                    String time = resultSet.getString(3);
                    message.setTime(time.substring(0, time.length() - 2));
                    messages.add(message);
                } while (resultSet.next());
                resultSet.close();
            }
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ArrayList<Message> mm=new ArrayList<Message>();
            mm.add(new Message("$","$"));
            return mm;
        }
        return messages;
    }
}
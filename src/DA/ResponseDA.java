package DA;

import PD.Response;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mingtao on 15-11-4.
 */
public class ResponseDA {
    static public void add(Response response){
        String sql="INSERT INTO response(Id,Content,Time,CommentId,FromUser,ToUser)" +
                "VALUES ('"+response.getID()+"','"+response.getContent()+"','"+
                response.getTime()+"','"+response.getCommentID()+"','"+
                response.getFromUserID()+"','"+response.getToUserID()+"')";
        try{
            Base.statement.executeUpdate(sql);
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}

package DA;

import PD.Response;

import java.sql.SQLException;

/**
 * Created by mingtao on 15-11-4.
 */
public class ResponseDA {
    static public void add(Response response){
        String sql="INSERT INTO response(Content,Time,CommentId,FromUser,ToUser)" +
                "VALUES ('"+response.getContent()+"','"+
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
    static public void delete(String ID){
        String sql="DELETE FROM respond WHERE Id='"+ID+"'";
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

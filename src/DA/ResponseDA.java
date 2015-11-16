package DA;

import PD.Response;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mingtao on 15-11-4.
 */
public class ResponseDA {
    static public void add(Response response){
        String sql="INSERT INTO response(Content,CommentId,FromUser,ToUser)" +
                "VALUES ('"+response.getContent()+"','"+response.getCommentId()+"','"+
                response.getFromUser()+"','"+response.getToUser()+"')";
        try{
            Base.statement.executeUpdate(sql);
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    static public ArrayList<Response> find(String commentId){
        String sql = "SELECT * FROM response WHERE CommentId='"+commentId+"'";
        Response response;
        ArrayList<Response>responses = new ArrayList<Response>();
        try{
            ResultSet resultSet=Base.statement.executeQuery(sql);
            if(!resultSet.next()){
                resultSet.close();
            }
            else {
                do {
                    response = new Response(resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4),resultSet.getString(5));
                    response.setId(resultSet.getString(1));
                    response.setTime(resultSet.getDate(3));
                    responses.add(response);
                }while (resultSet.next());
                resultSet.close();
            }
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return responses;
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

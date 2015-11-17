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
        String sql="INSERT INTO Response(Content,CommentId,FromUser,ToUser)" +
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
        String sql = "SELECT * FROM Response WHERE CommentId='"+commentId+"'";
        Response response;
        ArrayList<Response>responses = new ArrayList<Response>();
        try{
            ResultSet resultSet=Base.statement.executeQuery(sql);
            if(!resultSet.next()){
                resultSet.close();
            }
            else {
                do {
                    response = new Response(resultSet.getString(2), resultSet.getString(4),
                            resultSet.getString(5),resultSet.getString(6));
                    response.setId(resultSet.getString(1));
                    String time=resultSet.getString(3);//去掉最后“.0”
                    response.setTime(time.substring(0, time.length()-2));
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
        String sql="DELETE FROM Response WHERE Id='"+ID+"'";
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

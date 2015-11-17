package DA;

import PD.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;

/**
 * Created by mingtao on 15-11-4.
 */
public class CommentDA {
    static public void add(Comment comment){
        String sql="INSERT INTO Comment(Content,ArticleId,User)" +
                "VALUES ('"+comment.getContent()+"','"+comment.getArticleId()+"','"+comment.getUser()+"')";
        try{
            Base.statement.executeUpdate(sql);
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    static public ArrayList<Comment> find(String articleId){
        String sql = "SELECT * FROM Comment WHERE ArticleId='"+articleId+"'";
        Comment comment;
        ArrayList<Comment>comments = new ArrayList<Comment>();
        try{
            ResultSet resultSet=Base.statement.executeQuery(sql);
            if(!resultSet.next()){
                resultSet.close();
                comments=null;///??????????????????
            }
            else {
                do {
                    comment = new Comment(resultSet.getString(4), resultSet.getString(2), resultSet.getString(5));
                    comment.setId(resultSet.getString(1));
                    String time=resultSet.getString(3);//去掉最后“.0”
                    comment.setTime(time.substring(0,time.length()-2));
                    //comment.setResponses(ResponseDA.find(comment.getId()));
                    comments.add(comment);
                }while (resultSet.next());
                resultSet.close();
            }
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return comments;
    }
    static public void delete(String ID){
        String sql="DELETE FROM Comment WHERE Id='"+ID+"'";
        try{
            Base.statement.executeUpdate(sql);
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    public static ArrayList<String> getCommentId(String articleId){//?????????????
        String sql = "SELECT id FROM Comment WHERE ArticleId='"+articleId+"'";
        ArrayList<String> commentId=new ArrayList<String>();
        try {
            ResultSet resultSet=Base.statement.executeQuery(sql);
            if(!resultSet.next()){
                resultSet.close();
                commentId=null;
            }
            else {
                do {
                    commentId.add(resultSet.getString(1));
                }while (resultSet.next());
                resultSet.close();
            }
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return commentId;
    }
}

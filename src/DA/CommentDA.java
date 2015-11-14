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
        String sql="INSERT INTO comment(Content,ArticleId,User)" +
                "VALUES ('"+comment.getContent()+"','"+comment.getArticleId()+"','"+comment.getUserId()+"')";
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
        String sql = "SELECT * FROM comment WHERE ArticleId='"+articleId+"'";
        Comment comment;
        ArrayList<Comment>comments = new ArrayList<Comment>();
        try{
            ResultSet resultSet=Base.statement.executeQuery(sql);
            if(!resultSet.next()){
                resultSet.close();
            }
            else {
                do {
                    comment = new Comment(resultSet.getString(4), resultSet.getString(2), resultSet.getString(5));
                    comment.setId(resultSet.getString(1));
                    comment.setTime(resultSet.getDate(3));
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
        String sql="DELETE FROM comment WHERE Id='"+ID+"'";
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

package DA;

import PD.Article;
/**
 * Created by mingtao on 15-11-4.
 */
import java.text.DateFormatSymbols;
import java.sql.SQLException;
public class ArticleDA {
    static public void add(Article article){
        String sql="INSERT INTO article(Content,Time,ArticleId,User)" +
                "VALUES ('"+article.getContent()+"','"+
                article.getTime()+"','"+article.getArticleID()+"','"+article.getUserID()+"')";
        try{
            Base.statement.executeUpdate(sql);
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        static public void delete(String ID){
            String sql="DELETE FROM article WHERE Id='"+ID+"'";
            try{
                Base.statement.executeUpdate(sql);
            }
            catch (SQLException ex){
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
//        static public void query(String ID){
//        String sql="SELECT FROM comment WHERE Id='"+ID+"'";
//        try{
//            Base.statement.executeUpdate(sql);
//        }
//        catch (SQLException ex){
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }
    }
    }

}

package DA;

import PD.Article;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mingtao on 15-11-4.
 */
public class ArticleDA {
    static public int add(Article article) {
        String sql = "INSERT INTO Article(Titile, Content)" +
                "VALUES ('" + article.getTitle() + "','" +
                article.getContent() + "')";

        try {
            Base.statement.executeUpdate(sql);
            ResultSet rs=Base.statement.getGeneratedKeys();
            return  rs.getRow();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return 0;
    }

    static public void delete(int id) {
        String sql = "DELETE FROM article WHERE Id=" + id;
        try {
            Base.statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    static public int query(int ID) {
        String sql = "SELECT * FROM article WHERE Id="+ID;
        try {
            Base.statement.executeUpdate(sql);
            return ID;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return 0;
    }
}


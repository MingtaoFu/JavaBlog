package DA;

import PD.Article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ArticleDA {
    static public int add(Article article) {
        String sql = "INSERT INTO Article(Title, Content)" +
                "VALUES ('" + article.getTitle() + "','" +
                article.getContent() + "')";
        String sql1="SELECT LAST_INSERT_ID()";

        try {
            Base.statement.executeUpdate(sql);
            ResultSet rs = Base.statement.executeQuery(sql1); //获取结果
            if (rs.next()) {
                int index = rs.getInt(1);
                 return index;//取得ID
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return 0;
    }

    static public void delete(int id) {
        String sql = "DELETE FROM article WHERE ID=" + id;
        try {
            Base.statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    static public ArrayList query() {
        String sql = "SELECT * FROM Article";
        try {
            ResultSet rs = Base.statement.executeQuery(sql);
            ArrayList<Article> arrayList=new ArrayList<Article>();
            while (rs.next()){

                int a= rs.getInt("ID");
                System.out.println(a);
                Article getArticle=new Article(rs.getInt("ID"),rs.getString("Title"),rs.getString("Content"),rs.getString("Time"),rs.getString("ModifyTime"));
                System.out.print(rs);
                arrayList.add(getArticle);
            }
            return  arrayList;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }
    static public Article queryWithID(int id){
        String sql="SELECT * FROM article WHERE ID="+id;
        try{
            ResultSet rs =Base.statement.executeQuery(sql);
           if (rs.next()){
               Article getArticle= new Article(rs.getRow(),rs.getNString("Title"),rs.getNString("Content"),rs.getString("Time"),rs.getString("ModifyTime"));
               return getArticle;
           }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }
}


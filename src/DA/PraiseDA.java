package DA;

import PD.Article;
import PD.Praise;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by zouyingtian on 15/11/27.
 */
public class PraiseDA {
//    public  static int add(Praise praise){
//        String sql="INSERT INTO Praise(ArticleId,User)"+"VALUES (' "+praise.getArticleId()+"','"+praise.getUser()+"')";
//        try {
//            Base.statement.executeUpdate(sql);
//        } catch (SQLException ex) {
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//            return 0;
//        }
//        return 1;
//    }

//    public static void delete(int id){
//        String sql="DELETE FROM Praise WHERE ID="+id;
//        try{
//            Base.statement.executeUpdate(sql);
//        }catch (SQLException e){
//            System.out.println("SQLException: " + e.getMessage());
//            System.out.println("SQLState: " + e.getSQLState());
//            System.out.println("VendorError: " + e.getErrorCode());
//        }
//    }

    public static int queryCount(int articleId){
        String sql = "SELECT * FROM Praise";
        int count=0;
        try {
            ResultSet rs = Base.statement.executeQuery(sql);
            while (rs.next()){
                if (rs.getInt("ArticleId")==articleId){
                    count++;
                }
            }
            return  count;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return -1;
        }
    }

    //by Mingtao Fu
    public static boolean iStar(int articleId,String UserId) {
        String sqlquery = "SELECT articleId FROM Praise WHERE ArticleId='"+articleId+"'and User='"+UserId+"'";
        try {
            ResultSet rs = Base.statement.executeQuery(sqlquery);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        }  catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
                return false;
        }
    }

    public static int queryExist(int articleId,String UserId){
        String sqlquery = "SELECT articleId FROM Praise WHERE ArticleId='"+articleId+"'and User='"+UserId+"'";
        String sqldelete = "DELETE FROM Praise WHERE ArticleId='"+articleId+"'and User='"+UserId+"'";
        String sqladd ="INSERT INTO Praise(ArticleId,User)"+"VALUES (' "+articleId+"','"+UserId+"')";
        try {
            ResultSet rs = Base.statement.executeQuery(sqlquery);
            if(rs.next()) {
                Base.statement.executeUpdate(sqldelete);
                return 2;
            } else {
                Base.statement.executeUpdate(sqladd);
                return 1;
            }
            /*
            while (rs.next()) {
                if (rs.getInt("ArticleId") == articleId) {
                    Base.statement.executeUpdate(sqldelete);
                    return 2;
                }
                else if (rs.isLast()){
                    Base.statement.executeUpdate(sqladd);
                    return 1;
                }
            }
            if (!rs.next()) {
                Base.statement.executeUpdate(sqladd);
                return 1;
            }
            */
        } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
                return -1;
        }
    }

}

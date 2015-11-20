package DA;
import PD.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserDA {
    static User user;

    static String id;
    static String name;
    static String pwd;
    static Timestamp date;
    static String type;
    static String intro;
    static String logoUrl;
       //添加记录
    public static int add(User user){

        id = user.getId();
        name = user.getName();
        pwd = user.getPwd();
        date = user.getDate();
        type = user.getType();
        intro = user.getIntro();
        logoUrl = user.getLogoUrl();

        String sql = "INSERT INTO User (Id, Name, Pwd, Type)" +
                " VALUES ('" + id + "', '" + name + "', '" + pwd + "', '" + type +
                "')";

        System.out.println(sql);

        User c = find(id);
        if(c != null) {
            return 0;
        } else {
            try {
                int result = Base.statement.executeUpdate(sql);
                return 1;       //成功
            } catch (SQLException e2) {
                System.out.println(e2);
                return 2;       //SQL错误
            }
        }
    }

    public static boolean modifyPwd(User user, String newPwd) {
        String sql = "UPDATE User SET Pwd = '" + newPwd + "' WHERE Id = '" + user.getId() + "'";

        try {
            Base.statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static User find(String key) {
        user = null;

        String sql = "SELECT * FROM User WHERE Id = '" + key + "'";

        try {
            ResultSet rs = Base.statement.executeQuery(sql);
            boolean gotIt = rs.next();

            if(gotIt) {
                id = rs.getString("Id");
                name = rs.getString("Name");
                pwd = rs.getString("Pwd");
                date = rs.getTimestamp("Date");
                type = rs.getString("Type");
                intro = rs.getString("Intro");
                logoUrl = rs.getString("LogoUrl");

                user = new User(id, name, pwd, type, intro, date, logoUrl);
            } else {
                return null;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return user;
    }

    public static boolean modifyInfo(String id, String name, String intro) {
        String sql = "UPDATE User SET Name = '" + name + "' , Intro = '" + intro + "' WHERE Id = '" + id + "'";

        try {
            Base.statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void delete(User user) {
        id = user.getId();

        String sql = "DELETE FROM User WHERE Id = '" + id + "'";
        System.out.println(sql);
        try {
            int result = Base.statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    /*
    public static String[][] getAll() {
        String[][] arr = {};
        String sql = "SELECT * FROM Test";

        try {
            ResultSet rs = Base.statement.executeQuery(sql);
            boolean gotIt = rs.next();
           // String[][] data = {};
            ArrayList<Object> data = new ArrayList();

            while(rs.next()) {
                userID = rs.getString(1);
                name = rs.getString(2);
                password = rs.getString(3);

                String[] item = {userID, name, password};
                data.add(item);
                user = new User(userID, name, password);
            }
            int size=data.size();
            arr = new String[size][];
            for(int i=0;i<data.size();i++){
                arr[i]=(String[])data.get(i);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return arr;
    }
    */
}

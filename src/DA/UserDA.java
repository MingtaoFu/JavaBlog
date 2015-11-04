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
    public static int add(User user) throws DuplicateException{
        id = user.getId();
        name = user.getName();
        pwd = user.getPwd();
        date = user.getDate();
        type = user.getType();
        intro = user.getIntro();
        logoUrl = user.getLogoUrl();

        String sql = "INSERT INTO Test (Id, Name, Pwd, Type, Intro, Date, LogoUrl)" +
                " VALUES ('" + id + "', '" + name + "', '" + pwd + "', '" + type + "', '" +
                intro + "', '" + date + "', '" + logoUrl + "')";

        System.out.println(sql);

        try {
            User c = find(id);
            //throw new DuplicateException("用户名已存在");
            return 0;       //用户名存在
        } catch (NotFoundException e) {
            try {
                int result = Base.statement.executeUpdate(sql);
                return 1;       //成功
            } catch (SQLException e2) {
                System.out.println(e2);
                return 2;       //SQL错误
            }
        }
    }

    public static User find(String key) throws NotFoundException{
        user = null;
        String sql = "SELECT userID, Name, password FROM Test WHERE userID = '" + key + "'";

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
                throw (new NotFoundException("没有该记录"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return user;
    }

    public void update() {

    }

    public static void delete(User user) {
        id = user.getId();

        String sql = "DELETE FROM Test WHERE userID = '" + id + "'";
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

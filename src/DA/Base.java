package DA;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Objects;
import java.util.ArrayList;
public class Base {
    static Connection conn;
    static Statement statement;

	public static Connection initialize() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/JavaBlog?" +
							"user=hust&password=diaodiaodiao");

			statement = conn.createStatement();         //??
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	//关闭连接
	public static void terminate() {
		try {
			statement.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

    public static void createTables() {
        PreparedStatement preparedStatement;
        String[] sqlArr = {
                "create table User(Id varchar(32) not null, Name varchar(32) not null, Pwd varchar(32) not null, " +
                        "Date timestamp not null default 0, Type varchar(16) not null, Intro text, " +
                        "LogoUrl varchar(64) default '/static/logos/default', primary key (Id))",
                "create table Article(ID int not null auto_increment, Title varchar(64) not null, Content text, " +
                        "Time timestamp not null default 0, ModifyTime timestamp not null, primary key (Id))",
                "create table Comment(Id int not null auto_increment, Content text not null, Time timestamp not null default 0, " +
                        "ArticleId int not null, User varchar(32) not null, primary key (Id), " +
                        "foreign key (User) references User(Id))",
                "create table Response(Id int not null auto_increment, Content text not null, Time timestamp not null default 0, " +
                        "CommentId int not null, FromUser varchar(32) not null, ToUser varchar(32) not null, primary key (Id), " +
                        "foreign key (FromUser) references User(Id), foreign key (ToUser) references User(Id) )",
                "create Table Message(Id int not null auto_increment, Content text not null, Time timestamp not null default 0," +
                        "User varchar(32) not null, primary key(Id) )"
        };
        //依次执行各语句以建表，具体执行在CreateTables.java中
        for(int i = 0; i < sqlArr.length; i++) {
            try {
                preparedStatement = conn.prepareStatement(sqlArr[i]);
                preparedStatement.execute();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

/*
public class UserDA {
    static User user;

    //初始化
    public static Connection initialize() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                            "user=root&password=ningganmabu");

            statement = conn.createStatement();         //??
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }

    //关闭连接
    public static void terminate() {
        try {
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //添加记录
    public static void add(User user) throws DuplicateException{
        name = user.getName();
        userID = user.getUserID();
        password = user.getPassword();

        String sql = "INSERT INTO Test (UserID, Name, Password)" +
                " VALUES ('" + userID + "', '" + name + "', '" + password + "')";

        System.out.println(sql);
        try {
            User c = find(userID);
            throw new DuplicateException("用户名已存在");
        } catch (NotFoundException e) {
            try {
                System.out.println("ww");
                int result = statement.executeUpdate(sql);
            } catch (SQLException e2) {
                System.out.println(e2);
            }
        }
    }

    public static User find(String key) throws NotFoundException{
        user = null;
        String sql = "SELECT userID, Name, password FROM Test WHERE userID = '" + key + "'";

        try {
            ResultSet rs = statement.executeQuery(sql);
            boolean gotIt = rs.next();

            if(gotIt) {
                userID = rs.getString(1);
                name = rs.getString(2);
                password = rs.getString(3);

                user = new User(userID, name, password);
            } else {
                throw (new NotFoundException("没有该记录"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return user;
    }
    public void update() {}
    public static void delete(User user) {
        userID = user.getUserID();

        String sql = "DELETE FROM Test WHERE userID = '" + userID + "'";
        System.out.println(sql);
        try {
            int result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static String[][] getAll() {
        String[][] arr = {};
        String sql = "SELECT * FROM Test";
        try {
            ResultSet rs = statement.executeQuery(sql);
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
}

class NotFoundException extends Exception{
    public NotFoundException(String msg) {
        super(msg);
    }

}

class DuplicateException extends Exception{
    public DuplicateException(String msg) {
        super(msg);
    }
}
*/

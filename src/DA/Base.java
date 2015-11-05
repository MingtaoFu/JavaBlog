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
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			conn = DriverManager.getConnection("jdbc:mysql://localhost/javablog?" +
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
                        "Date timestamp not null default CURRENT_TIMESTAMP, Type varchar(16) not null, Intro text, " +
                        "LogoUrl varchar(64) default '/static/logos/default', primary key (Id))",
                "create table Article(ID int not null auto_increment, Title varchar(64) not null, Content text, " +
                        "Time timestamp not null default CURRENT_TIMESTAMP, ModifyTime timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP , primary key (Id))",
                "create table Comment(Id int not null auto_increment, Content text not null, Time timestamp not null default CURRENT_TIMESTAMP, " +
                        "ArticleId int not null, User varchar(32) not null, primary key (Id), " +
                        "foreign key (User) references User(Id))",
                "create table Response(Id int not null auto_increment, Content text not null, Time timestamp not null default CURRENT_TIMESTAMP, " +
                        "CommentId int not null, FromUser varchar(32) not null, ToUser varchar(32) not null, primary key (Id), " +
                        "foreign key (FromUser) references User(Id), foreign key (ToUser) references User(Id) )",
                "create Table Message(Id int not null auto_increment, Content text not null, Time timestamp not null default CURRENT_TIMESTAMP," +
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
/*
public class UserDA {
    static User user;




}


*/

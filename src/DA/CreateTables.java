package DA;

import PD.Comment;
import PD.Response;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 运行该文件以建表
 * 在此之前你需要为MySQL创建一个用户，名字为"hust"，密码为"diaodiaodiao"，并建一张名为"javablog"的表，且hust拥有对它的所有权限。
 * 具体做法(命令行)：
 *      mysql -u root -p
 *      (mysql) create database javablog;       //建表
 *      (mysql) create user 'hust'@'localhost' identified by 'diaodiaodiao';        //建用户
 *      (mysql) grant all privileges on javablog.* to hust@localhost identified by 'diaodiaodiao';      //授权
 */
public class CreateTables {
    public static void main(String[] args) {
        Base.initialize();
//      Base.createTables();
//      Base.terminate();
//      new Comment("2","3",new Timestamp(System.currentTimeMillis()),"1").add();
//      new Response("2",new Timestamp(System.currentTimeMillis()),"3","4","5").add();
//      Comment.delete("1");
    }
}

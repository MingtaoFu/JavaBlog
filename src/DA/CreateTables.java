package DA;

import PD.Comment;
import PD.Response;

import java.sql.Timestamp;
import java.util.ArrayList;
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

/**
 * 一定要是MySQL 5.6
 */
public class CreateTables {
    public static void main(String[] args) {
        Base.initialize();
//      Base.createTables();
//      Base.terminate();
//      new Comment("2","3","1").add();
//        for(int i=0;i<3;i++) {
//            ArrayList<Comment> c = CommentDA.find("2");
//            System.out.println(c.get(0).getId());
//            System.out.println(c.get(0).getContent());
//            System.out.println(c.get(0).getArticleId());
//            System.out.println(c.get(0).getTime());
//            System.out.println(c.get(0).getUserId());
//        }
//        for(int i=0;i<3;i++) {
//            ArrayList<Response> c = ResponseDA.find("3");
//            System.out.println(c.get(i).getId());
//            System.out.println(c.get(i).getContent());
//            System.out.println(c.get(i).getCommentId());
//            System.out.println(c.get(i).getTime());
//            System.out.println(c.get(i).getFromUser());
//            System.out.println(c.get(i).getToUser());
//        }
        //new Response("2","3","1","1").add();
//      Comment.delete("1");
    }
}

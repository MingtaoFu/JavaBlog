package DA;

/**
 * 运行该文件以建表
 * 在此之前你需要为MySQL创建一个用户，名字为"hust"，密码为"diaodiaodiao"，并建一张名为"JavaBlog"的表，且hust拥有对它的所有权限。
 * 具体做法(命令行)：
 *      mysql -u root -p
 *      (mysql) create database JavaBlog;       //建表
 *      (mysql) create user 'hust'@'localhost' identified by 'diaodiaodiao';        //建用户
 *      (mysql) grant all privileges on JavaBlog.* to hust@localhost identified by 'diaodiaodiao';      //授权
 */
public class CreateTables {
    public static void main(String[] args) {
        Base.initialize();
        Base.createTables();
        Base.terminate();
    }
}

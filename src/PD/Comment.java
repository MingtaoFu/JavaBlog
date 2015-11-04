package PD;

import DA.CommentDA;

import java.sql.Timestamp;

/**
 * Created by mingtao on 15-11-4.
 */
public class Comment {
    String ID;
    String articleID;
    String content;
    Timestamp time;
    String userID;


    public String getID() {
        return ID;
    }

    public String getArticleID() {
        return articleID;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getUserID() {
        return userID;
    }


    public void setID(String ID) {
        this.ID = ID;
    }

    public Comment(String articleID, String content, Timestamp time, String userID) {
        this.articleID = articleID;
        this.content = content;
        this.time = time;
        this.userID = userID;
    }

    public void add(){
        CommentDA.add(this);
    }
//    public void delete(){
//        CommentDA.delete(this);
//    }

}

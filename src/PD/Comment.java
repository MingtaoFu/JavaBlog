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

    String userID;

    public Comment(String ID, String articleID, String content, Timestamp time, String userID) {
        this.ID = ID;
        this.articleID = articleID;
        this.content = content;
        this.time = time;
        this.userID = userID;
    }

    public void add(Comment comment){
        CommentDA.add(comment);
    }

}

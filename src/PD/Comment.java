package PD;

import DA.CommentDA;

import java.util.Date;

/**
 * Created by mingtao on 15-11-4.
 */
public class Comment {
    String ID;
    String articleID;
    String content;
    Date time;

    public String getID() {
        return ID;
    }

    public String getArticleID() {
        return articleID;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return time;
    }

    public String getUserID() {
        return userID;
    }

    String userID;

    public Comment(String ID, String articleID, String content, Date time, String userID) {
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

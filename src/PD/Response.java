package PD;

import DA.ResponseDA;

import java.sql.Timestamp;

/**
 * Created by mingtao on 15-11-4.
 */
public class Response {
    String ID;
    String content;
    Timestamp time;
    String commentID;
    String fromUserID;
    String toUserID;

    public String getID() {
        return ID;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getCommentID() {
        return commentID;
    }

    public String getFromUserID() {
        return fromUserID;
    }

    public String getToUserID() {
        return toUserID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Response( String content, Timestamp time, String commentID, String fromUserID, String toUserID) {
        this.content = content;
        this.time = time;
        this.commentID = commentID;
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
    }
    public void add(){
        ResponseDA.add(this);
    }
//    public void delete(){
//        ResponseDA.delete(this);
//    }
}

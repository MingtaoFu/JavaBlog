package PD;

import DA.ResponseDA;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by mingtao on 15-11-4.
 */
public class Response {
    String id;
    String content;
    Timestamp time;
    String commentId;
    String fromUser ;
    String toUser ;

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getFromUser () {
        return fromUser ;
    }

    public String getToUser () {
        return toUser ;
    }

    public void setId(String ID) {
        this.id = ID;
    }
    public void setTime(Timestamp time)
    {
        this.time=time;
    }

    public Response( String content,  String commentID, String fromUserID, String toUserID) {
        this.content = content;
        this.commentId = commentID;
        this.fromUser  = fromUserID;
        this.toUser  = toUserID;
    }
    public void add(){
        ResponseDA.add(this);
    }
    public ArrayList<Response> find(String commentId){return ResponseDA.find(commentId);}
    public static void delete(String ID){
        ResponseDA.delete(ID);
    }
}

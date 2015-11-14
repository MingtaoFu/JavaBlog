package PD;

import DA.ResponseDA;

import javax.xml.bind.annotation.XmlElement;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by mingtao on 15-11-4.
 */
public class Response {
    String id;
    Date time;
    @XmlElement()
    String content;
    @XmlElement()
    String commentId;
    @XmlElement()
    String fromUser ;
    @XmlElement()
    String toUser ;

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
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
    public void setTime(Date time)
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

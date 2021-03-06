package PD;

import DA.ResponseDA;
import com.sun.xml.internal.txw2.annotation.XmlNamespace;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;

/**
 * Created by mingtao on 15-11-4.
 */
//@XmlType(name = "response")
//@XmlRootElement(name = "response")

public class Response {
    private int id;
    private String time;
    private String content;
    private String commentId;
    private String fromUser ;
    private String toUser ;

    public Response() {
    }

    @XmlElement()
    public int getId() {
        return id;
    }
    @XmlElement()
    public String getContent() {
        return content;
    }
    @XmlElement()
    public String  getTime() {
        return time;
    }
    @XmlElement()
    public String getCommentId() {
        return commentId;
    }
    @XmlElement()
    public String getFromUser () {
        return fromUser ;
    }
    @XmlElement()
    public String getToUser () {
        return toUser ;
    }

    public void setId(int ID) {
        this.id = ID;
    }
    public void setTime(String time)
    {
        this.time=time;
    }

    public Response( String content,  String commentID, String fromUser, String toUser) {
        this.content = content;
        this.commentId = commentID;
        this.fromUser  = fromUser;
        this.toUser  = toUser;
    }
    public int add(){
        return ResponseDA.add(this);
    }
    public static ArrayList<Response> find(int commentId){return ResponseDA.find(commentId);}
    public static int delete(int ID){
        return ResponseDA.delete(ID);
    }
}

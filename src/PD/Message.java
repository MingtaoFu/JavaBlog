package PD;

import DA.MessageDA;

import javax.xml.bind.annotation.XmlElement;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by mingtao on 15-11-4.
 */
public class Message {
    int Id;
    String content;
    String  time;
    String userId;

    public Message(String content,String userID) {
        this.content = content;
//        this.time = time;
        this.userId = userID;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @XmlElement()
    public int getId() {
        return Id;
    }
    @XmlElement()
    public String getContent() {
        return content;
    }
    @XmlElement()
    public String getTime() {
        return time;
    }
    @XmlElement()
    public String getUserId() {
        return userId;
    }
    public int add(){
       return MessageDA.add(this);
    }
    public static ArrayList<Message> find(){return MessageDA.find();}
    public static int delete(int Id){
        return MessageDA.delete(Id);
    }
}

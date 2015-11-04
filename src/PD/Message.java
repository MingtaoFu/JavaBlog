package PD;

import DA.CommentDA;
import DA.MessageDA;

import java.sql.Timestamp;

/**
 * Created by mingtao on 15-11-4.
 */
public class Message {
    String ID;
    String content;
    Timestamp time;
    String userID;

    public Message(String content, Timestamp time, String userID) {
        this.content = content;
        this.time = time;
        this.userID = userID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
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
    public void add(){
        MessageDA.add(this);
    }
    public static void delete(String ID){
        MessageDA.delete(ID);
    }
}

package PD;

import DA.CommentDA;

import javax.xml.bind.annotation.XmlElement;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by mingtao on 15-11-4.
 */
public class Comment {
    String id;
    Date time;
    @XmlElement()
    String articleId;
    @XmlElement()
    String content;
    @XmlElement()
    String user;


    public String getId() {
        return id;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return time;
    }

    public String getUserId() {
        return user;
    }


    public void setId(String ID) {
        this.id = ID;
    }
    public void setTime(Date time) {
        this.time = time;
    }

    public Comment(String articleID, String content, String userID) {
        this.articleId = articleID;
        this.content = content;
        //this.time = time;
        this.user  = userID;
    }

    public void add(){
        CommentDA.add(this);
    }
    public ArrayList<Comment> find(String articleId){return CommentDA.find(articleId);}
    public static void delete(String ID){
        CommentDA.delete(ID);
    }

}

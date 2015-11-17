package PD;

import DA.CommentDA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by mingtao on 15-11-4.
 */
//@XmlType(name = "comment")
//    @XmlRootElement(name = "comment")
public class Comment {
    private String id;
    private String time;
    private String articleId;
    private String content;
    private String user;
//    @XmlElement()//???????????????????????????????
//    ArrayList<Response> responses;

    public Comment() {
    }
    @XmlElement()
    public String getId() {
        return id;
    }
    @XmlElement()
    public String getArticleId() {
        return articleId;
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
    public String getUser() {
        return user;
    }

//    public void setResponses(ArrayList<Response> responses) {
//        this.responses = responses;
//    }

    public void setId(String ID) {
        this.id = ID;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public Comment(String articleID, String content, String user) {
        this.articleId = articleID;
        this.content = content;
        //this.time = time;
        this.user  = user;
    }

    public void add(){
        CommentDA.add(this);
    }
    public static ArrayList<Comment> find(String articleId){return CommentDA.find(articleId);}
    public static void delete(String ID){
        CommentDA.delete(ID);
    }
    public static ArrayList<String> getCommentId(String articleId){//???????????
        return CommentDA.getCommentId(articleId);
    }
}

package PD;

import DA.CommentDA;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

/**
 * Created by mingtao on 15-11-4.
 */
//@XmlType(name = "comment")
//    @XmlRootElement(name = "comment")
public class Comment {
    private int id;
    private String time;
    private String articleId;
    private String content;
    private String user;
    private String logoUrl;
    private String name;
//    @XmlElement()//???????????????????????????????
//    ArrayList<Response> responses;

    public Comment() {
    }
    @XmlElement()
    public String getLogoUrl() {
        return logoUrl;
    }
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    @XmlElement()
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @XmlElement()
    public int getId() {
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

    public void setId(int ID) {
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

    public int add(){
        return CommentDA.add(this);
    }
    public static ArrayList<Comment> find(int articleId){
        return CommentDA.find(articleId);
    }
    public static int delete(int ID){
        return CommentDA.delete(ID);
    }
    public static int getCommentNum(int articleId){return CommentDA.getCommentNum(articleId);}

    public static ArrayList getCommentUserInfo(int commentId){return CommentDA.getCommentUserInfo(commentId);}
//    public static ArrayList<String> getCommentId(String articleId){//???????????
//        return CommentDA.getCommentId(articleId);
//    }
}

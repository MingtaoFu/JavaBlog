package Service;


import PD.*;
import PD.Comment;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
@XmlSeeAlso({ArrayList.class})
class RT_All{
    @XmlElement()
    private int status;
//    @XmlElement()
//    private int index;
//    @XmlElement()
//    private String title;
//    @XmlElement()
//    private String content;
//    @XmlElement()
//    private Date modifyTime;
    private ArrayList<PD.Comment> comments;
//    @XmlElement()
//    private ArrayList commentUserInfo;
    private ArrayList<PD.Response> responses;
    private PD.Article article;

    public int getStatus() {
        return status;
    }
//
//    public void setCommentUserInfo(ArrayList commentUserInfo) {
//        this.commentUserInfo = commentUserInfo;
//    }
//
//    public ArrayList getCommentUserInfo() {
//        return commentUserInfo;
//    }
    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<PD.Comment> getComment() {
    return comments;
}
    public ArrayList<PD.Response> getResponse() {
        return responses;
    }
    public PD.Article getArticle(){return  article;}

    public RT_All(){}

    public RT_All(int status) {
        this.status = status;
    }
    public RT_All(int status,ArrayList<PD.Comment> comments,ArrayList<PD.Response> responses,PD.Article article) {
        this.status = status;
        this.article = article;
        this.comments = comments;
        this.responses = responses;
    }
}
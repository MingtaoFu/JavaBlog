package Service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


//添加评论
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_AddComment{
    @XmlElement()
    private int status;

    public RT_AddComment(int status){
        this.status = status;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_DeleteComment {
    @XmlElement()
    private int status;

    public RT_DeleteComment(int status) {
        this.status = status;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_getAllComment {
    @XmlElement()
    private ArrayList<Comment> comments;

    public RT_getAllComment() {
    }

    public RT_getAllComment(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
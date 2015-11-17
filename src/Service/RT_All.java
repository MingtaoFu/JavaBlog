package Service;


import PD.*;
import PD.Comment;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
//@XmlSeeAlso({RT_GetAllResponse.class})
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
    private ArrayList<PD.Response> responses;


    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<PD.Comment> getComment() {
    return comments;
}
    public ArrayList<PD.Response> getResponse() {
        return responses;
    }

    public RT_All(){}

    public RT_All(int status) {
        this.status = status;
    }
    public RT_All(int status,ArrayList<PD.Comment> comments,ArrayList<PD.Response> responses) {
        this.status=status;
        this.comments = comments;
        this.responses = responses;
    }
}
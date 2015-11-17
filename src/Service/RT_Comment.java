package Service;

import PD.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;


//添加评论
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
//@XmlSeeAlso({RT_GetAllResponse.class})
class RT_AddComment{
    @XmlElement()
    private int status;


    public RT_AddComment() {
    }

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

//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name = "data")
//class RT_GetAllComment {
//    @XmlElement()
//    private ArrayList<PD.Comment> comments;
////    @XmlElement()
//////    private int status;
////    @XmlElement()
////    private ArrayList<RT_GetAllResponse> allResponses;
//
//    public RT_GetAllComment() {
//    }
//
//    public RT_GetAllComment(ArrayList<PD.Comment> comments) {
//        this.comments = comments;
//        //    this.status = status;
////        this.allResponses = allResponses;
//    }
//}
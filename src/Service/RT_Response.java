package Service;


import PD.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_AddResponse{
    @XmlElement()
    private int status;

    public RT_AddResponse() {
    }
    public RT_AddResponse(int status){
        this.status = status;
    }
}

class RT_DeleteResponse{
    @XmlElement()
    private int status;

    public RT_DeleteResponse() {
    }
    public RT_DeleteResponse(int status){
        this.status=status;
    }
}

//class RT_GetAllResponse{
////    @XmlElement()
////    private int status;
//        @XmlElement()
//        private ArrayList<PD.Response> responses;
//
//        public RT_GetAllResponse( ArrayList<PD.Response> responses) {
//            this.responses = responses;
//        }
//
//        public RT_GetAllResponse() {
//
//        }
//}
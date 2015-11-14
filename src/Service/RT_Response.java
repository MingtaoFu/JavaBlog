package Service;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
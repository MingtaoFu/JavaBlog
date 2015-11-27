package Service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_AddMessage{
    @XmlElement()
    private int status;
    public RT_AddMessage() {
    }
    public RT_AddMessage(int status){
        this.status = status;
    }
}
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_DeleteMessage {
    @XmlElement()
    private int status;

    public RT_DeleteMessage() {
    }
    public RT_DeleteMessage(int status) {
        this.status = status;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_GetAllMessage {
    @XmlElement()
    private int status;
    private ArrayList<PD.Message> messages;


    public int getStatus() {
        return status;
    }

    public ArrayList<PD.Message> getMessages() {
        return messages;
    }

    public RT_GetAllMessage() {
    }

    public RT_GetAllMessage(int status, ArrayList<PD.Message> messages) {
        this.status = status;
        this.messages = messages;
    }

    public RT_GetAllMessage(int status) {

        this.status = status;
    }
}
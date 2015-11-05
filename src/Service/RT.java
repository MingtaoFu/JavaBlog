package Service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mingtao on 15-11-4.
 */
public class RT {


}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_Register{
    @XmlElement(name = "status")
    private int status;
    @XmlElement(name = "token")
    private String token;
    public RT_Register() {}
    public RT_Register(int status, String token){
        setStatus(status);
        setToken(token);
    }

    public int getStatus() {
                         return status;
                                                    }

    public void setStatus(int status) {
                                    this.status = status;
                                                                                 }

    public String getToken() {
                           return token;
                                                       }

    public void setToken(String token) {
                                     this.token = token;
                                                                                 }
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_Login{
    @XmlElement(name = "status")
    private int status;
    @XmlElement(name = "token")
    private String token;

    public RT_Login(){}
    public RT_Login(int status, String token) {
        this.status = status;
        this.token = token;
    }
}


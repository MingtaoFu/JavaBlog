package Service;

import PD.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by mingtao on 15-11-4.
 */
public class RT {


}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_Register{
    @XmlElement()
    private int status;
    @XmlElement()
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
    @XmlElement()
    private int status;
    @XmlElement()
    private String token;

    public RT_Login(){}
    public RT_Login(int status, String token) {
        this.status = status;
        this.token = token;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_All{
    @XmlElement()
    private int status;
    @XmlElement()
    private String  data;

    public RT_All(){}
    public RT_All(int status, String  data) {
        this.status = status;
        this.data = data;
    }
}
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_PersonalInfo{
    @XmlElement()
    private int status;
    @XmlElement()
    private String id;
    @XmlElement()
    private String name;
    @XmlElement()
    private Date date;
    @XmlElement()
    private String type;
    @XmlElement()
    private String intro;
    @XmlElement()
    private String logoUrl;

    public RT_PersonalInfo(){}

    public RT_PersonalInfo(int status, User user) {
        this.status = status;

        if(user != null) {
            this.id = user.getId();
            this.name = user.getName();
            this.date = user.getDate();
            this.type = user.getType();
            this.intro = user.getIntro();
            this.logoUrl = user.getLogoUrl();
        }

    }

}


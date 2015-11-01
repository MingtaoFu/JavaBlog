package PD;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "userData")
public class User {
    @XmlElement(name = "id")
    String id;
    @XmlElement(name = "name")
    String name;
    @XmlElement(name = "pwd")
    String pwd;
    @XmlElement(name = "type")
    String type;
    @XmlElement(name = "intro")
    String intro;
    @XmlElement(name = "registerTime")
    Date date;
    @XmlElement(name = "logoUrl")
    String logoUrl;

    public User() {}
    public User(String id, String name, String pwd, String type, String intro, Date date, String logoUrl) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.type = type;
        this.intro = intro;
        this.date = date;
        this.logoUrl = logoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", type='" + type + '\'' +
                ", intro='" + intro + '\'' +
                ", date=" + date +
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }
}

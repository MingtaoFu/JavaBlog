package PD;

import DA.UserDA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import javax.xml.bind.annotation.XmlRootElement;
import redis.clients.jedis.Jedis;

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
    Timestamp date;
    @XmlElement(name = "logoUrl")
    String logoUrl;

    public User() {}
    public User(String id, String name, String pwd, String type, String intro, Timestamp date, String logoUrl) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.type = type;
        this.intro = intro;
        this.date = date;
        this.logoUrl = logoUrl;
    }

    //生成token
    public String generateToken() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static int add(User user) {
        try {
            int status = UserDA.add(user);
            return status;
        } catch (Exception e) {
            return 0;       //已存在
        }
    }

    //验证
    public boolean validate() {
        return false;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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

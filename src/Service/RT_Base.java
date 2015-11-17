package Service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mingtao on 11/15/15.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
public class RT_Base {
    @XmlElement()
    private int status;

    public RT_Base() {}
    public RT_Base(int status) {
        this.status = status;
    }
}


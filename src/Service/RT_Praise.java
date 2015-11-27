/**
 * Created by zouyingtian on 15/11/27.
 */
package Service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_AddPraise{
    @XmlElement()
    private int status;
    @XmlElement()
    private int articleId;
    @XmlElement()
    private int count;

    public RT_AddPraise() {
    }
    public RT_AddPraise(int status,int articleId,int count){
        this.status = status;
        this.articleId=articleId;
        this.count=count;
    }
}

//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name = "data")
//class RT_DeletePraise{
//    @XmlElement()
//    private int status;
//    @XmlElement()
//    private int articleId;
//    @XmlElement()
//    private int count;
//
//    public RT_DeletePraise() {
//    }
//    public RT_DeletePraise(int status,int articleId,int count){
//        this.status = status;
//        this.articleId=articleId;
//        this.count=count;
//    }
//}
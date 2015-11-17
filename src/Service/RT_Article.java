/**
 * Created by zouyingtian on 15/11/9.
 */
package Service;

import PD.Article;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.util.Date;


//发表文章
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_Publish{
    @XmlElement()
    private int status;
    @XmlElement()
    private int index;
    public RT_Publish(int status,int index){
        this.status = status;
        this.index=index;
    }
}

//修改文章
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_Modify{
    @XmlElement()
    private int status;
    @XmlElement()
    private int index;
    public  RT_Modify(int status, int index){
        this.status=status;
        this.index=index;

    }


}
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
class RT_ArticleList{
    @XmlElement()
    private int status;
    @XmlElement()
    private int index;
    @XmlElement()
    private String title;
    @XmlElement()
    private String content;
    @XmlElement()
    private Date modifytime;
    public RT_ArticleList(int status,int index,String title,String content,Date modifytime){
        this.status = status;
        this.index=index;
        this.title=title;
        this.content=content;
        this.modifytime=modifytime;
    }
}

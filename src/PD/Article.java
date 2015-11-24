package PD;


import java.sql.Timestamp;
import DA.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by zouyingtian on 15/11/12.
 */
public class Article {
	int id;
	//int index;
	String title;
	String content;
	String time;
	String modifyTime;
    int commentNum;

    @XmlElement()
	public int getId() {return id;}
    @XmlElement()
    public String getTitle() {return title;}
    @XmlElement()
    public String getModifyTime () {
        return modifyTime;
//        String modifyTimeStr = "";
//        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        try {
//            modifyTimeStr = sdf.format(modifyTime);
//            return modifyTimeStr;
//        } catch (Exception e) {
//            e.printStackTrace();
//            modifyTimeStr="time get error";
//            return modifyTimeStr;
//        }
    }
    @XmlElement()
    public String getContent() {
        return content;
    }
    @XmlElement()
    public  String getTime(){return  time;}
    @XmlElement()
    public int getCommentNum() {return commentNum;}

    public void setCommentNum(int comments){this.commentNum=comments;}

    public void setIndex (int id){
            this.id = id;
        }

        public Article( int id, String title, String content, String time, String modifyTime){
            this.id=id;
            this.title = title;
            this.content = content;
            this.time = time;
            this.modifyTime = modifyTime;
        }


        public int add () {
            return ArticleDA.add(this);
        }


        public static void delete ( int index){
            ArticleDA.delete(index);
        }
        public  static ArrayList queryList(){
            return ArticleDA.query();
        }

        public  static Article queryWithID(int index){return ArticleDA.queryWithID(index);}
    }

package PD;


import java.sql.Timestamp;
import DA.*;

/**
 * Created by zouyingtian on 15/11/12.
 */
public class Article {
	int id;
	//int index;
	String title;
	String content;
	Timestamp time;
	Timestamp modifyTime;


	public int getId() {return id;}


    public String getTitle() {return title;}
    public Timestamp getModifyTime () {
            return modifyTime;
        }

    public String getContent() {
        return content;
    }


    //        public Article(String id, String title, String content, Timestamp time, Timestamp modifyTime) {
//            this.id = id;}
    public void setIndex (int id){
            this.id = id;
        }

        public Article( int id, String title, String content, Timestamp time, Timestamp modifyTime){
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

//        public static Article query(int index){
//            return  ArticleDA.query(index);
//        }
        //public  static


    }

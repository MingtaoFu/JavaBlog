package PD;


import java.sql.Timestamp;
import DA.*;

/**
 * Created by zouyingtian on 15/11/12.
 */
public class Article {
	String id;
	int index;
	String title;
	String content;
	Timestamp time;
	Timestamp modifyTime;


	public String getId() {return id;}
    public int getIndex() {
			return index;
		}

    public String getTitle() {return title;}
    public Timestamp getModifyTime () {
            return modifyTime;
        }

    public String getContent() {
        return content;
    }


    //        public Article(String id, String title, String content, Timestamp time, Timestamp modifyTime) {
//            this.id = id;}
    public void setIndex ( int index){
            this.index = index;
        }

        public Article( int index, String title, String content, Timestamp time, Timestamp modifyTime){
            this.index = index;
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


    }

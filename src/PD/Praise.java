package PD;


import DA.PraiseDA;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by zouyingtian on 15/11/27.
 */
public class Praise {

        private int id;
        private String time;
        private int articleId;
        private String user;
        @XmlElement()
        public int getId() {
            return id;
        }
        @XmlElement()
        public int getArticleId() {
            return articleId;
        }
        @XmlElement()
        public String getTime() {
            return time;
        }
        @XmlElement()
        public String getUser() {
            return user;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Praise(int articleId,String user) {
            this.articleId=articleId;
            this.user=user;

        }
//        public  int add(){return PraiseDA.add(this);}
//
//        public static void delete(int index){PraiseDA.delete(index);}

        public int getCount(int Id){ return  PraiseDA.queryCount(Id);}

        public int isPraised(){
            return PraiseDA.queryExist(this.articleId,this.user);
        }

}

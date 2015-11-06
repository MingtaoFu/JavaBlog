package PD;

import DA.ArticleDA;

import java.sql.Timestamp;


/**
 * Created by mingtao on 15-11-4.
 */

public class Article {
    String id;
    String title;
    String content;
    Timestamp time;
    Timestamp modifyTime;


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTime() {
        return time;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public Article(String id, String title, String content, Timestamp time, Timestamp modifyTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
        this.modifyTime = modifyTime;
    }

    public void add(){
        ArticleDA.add(this);
    }
    public static void delete(String id){
        ArticleDA.delete(id);
    }
}


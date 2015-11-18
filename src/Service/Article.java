
package Service;

import javax.ws.rs.*;

import DA.Base;
import DA.ArticleDA;
import PD.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by zouyingtian on 15/11/7.
 */

@Path("api/article")
public class Article {
    /*
    (传入表单里title和content,从cookie获取id和token,如果是root类型,则可以调用此方法,返回status,返回的index是数据库里的id值
    status值与意义:0 登录失败
                  1 发表成功
                  2 数据库写入错误
                  3 用户权限为normal
     */
    @POST
    @Path("publish")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_Publish publish(@FormParam("title") String title,
                                @FormParam("content") String content,@CookieParam("id") String id,
                             @CookieParam("token") String token) {

        Base.initialize();
        RT_Publish publish;
        User currentUser=User.validate(id,token);
            PD.Article article=new PD.Article(0,title,content,null,new Timestamp(System.currentTimeMillis()));
        if (currentUser != null && currentUser.getType().equals("root")){
            int index = article.add();
            if (index==0){
                publish=new RT_Publish(2,0);
            }
            else {
                article.setIndex(index);
                publish = new RT_Publish(1, article.getId());
            }
        }
        else if (currentUser != null && currentUser.getType().equals("normal")){
            publish = new RT_Publish(3,0);
        }
        else  publish= new RT_Publish(0,0);
        Base.terminate();
        return  publish;
    }


    @POST
    @Path("modify")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_Modify modify(@FormParam("title") String title,
                            @FormParam("id") int index,
                            @FormParam("content") String content,@CookieParam("id") String id,
                            @CookieParam("token") String token){
        Base.initialize();
        RT_Modify modify;

        User currentUser=User.validate(id,token);
        PD.Article article;
        if (currentUser != null && currentUser.getType().equals("root")){
            modify= new RT_Modify(1,index);
        }
        else {
           modify =new RT_Modify(0,0);
        }
        Base.terminate();
        return  modify;
    }


    @GET
    @Path("articleList")
    @Produces(MediaType.APPLICATION_JSON)
    public  RT_ArticleList articleList() {
        Base.initialize();
        ArticleDA articleDA=new ArticleDA();
        RT_ArticleList articleList=new RT_ArticleList(1,articleDA.query());
        Base.terminate();
        return  articleList;
    }
    @GET
    @Path("articleContent")
    @Produces(MediaType.APPLICATION_JSON)
    public  RT_ArticleContent articleContent(@QueryParam("id")int index){
        Base.initialize();
        ArticleDA articleDA = new ArticleDA();
        PD.Article article=articleDA.queryWithID(index);
        RT_ArticleContent articleContent=new RT_ArticleContent(1,article.getId(),article.getTitle(),article.getContent(),article.getModifyTime());
        Base.terminate();
        return articleContent;
    }
}


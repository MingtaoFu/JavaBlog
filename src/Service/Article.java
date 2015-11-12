
package Service;

import javax.ws.rs.*;

import DA.Base;
import PD.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by zouyingtian on 15/11/7.
 */

@Path("api/article")
public class Article {
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
        if (currentUser.getType().equals("root")){
            publish= new RT_Publish(1,article.getIndex());
            article.setIndex(article.add());
        }
        else {
            publish = new RT_Publish(0,0);
        }
        Base.terminate();
        return  publish;
    }


//    @POST
//    @Path("modify")
//    @Produces(MediaType.APPLICATION_JSON)
//    public RT_Modify modify(@FormParam("title") String title,
//                            @FormParam("content") String content,@CookieParam("id") String id,
//                            @CookieParam("token") String token){
//        Base.initialize();
//        RT_Modify modify;
//
//        User currentUser=User.validate(id,token);
//        PD.Article article=new PD.Article(0,title,content,null,new Timestamp(System.currentTimeMillis());
//
//        if (currentUser.getType().equals("root")){
//            modify= new RT_Modify(1,article.getIndex(),title,content,new Timestamp(System.currentTimeMillis());
//
//            //   article.setIndex(article.add());
//        }
//        else {
//           // modify =new RT_Modify(0,0,);
//            //modify = new RT_Publish(0,0);
//        }
//        Base.terminate();
//        return  modify;
//    }
//    @GET
//    @Path("artitle")
//    @Produces(MediaType.APPLICATION_JSON)
//    public RT_ArticleList articleList(@FormParam("title")String title,@CookieParam("id") String id,
//                                      @CookieParam("token") String token){
//        Base.initialize();
//        RT_ArticleList articleList;
//        User currentUser=User.validate(id,token);
//        PD.Article article=new PD.Article(0,title,null,null,new Timestamp(System.currentTimeMillis());
//
//        if (currentUser.getType().equals("root")){
//            articleList = new RT_Modify(1,article.getIndex(),title,null,new Timestamp(System.currentTimeMillis());
//
//        }
//        else {
//
//        }
//        return articleList;
//    }

}


package Service;

import DA.Base;
import PD.*;
import PD.Article;
import PD.Response;

import javax.print.DocFlavor;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.Provider;
import java.util.ArrayList;

/**
 * Created by pc on 2015/11/14.
 */
@Path("api/article")
public class GetAll {
    @GET
    @Path("oneArticleContent")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_All getAll(@QueryParam("articleId") String articleId
                          /*@CookieParam("id") String id,
                         @CookieParam("token")String token*/){
        Base.initialize();
//        if(id==null||token==null){
//            Base.terminate();
//            return new RT_All(0);
//        }
        RT_All all;
//        User currentUser=User.validate(id, token);
//        if (currentUser==null){
//            all=new RT_All(2);
//        }
//        else {
//      article属性
//        ArrayList<ArrayList<PD.Response>> responses = new ArrayList<ArrayList<PD.Response>>();
//        ArrayList<PD.Response>response = new ArrayList<PD.Response>();

            ArrayList<PD.Response> response1 = new ArrayList<PD.Response>();
            ArrayList<PD.Response> response2 = new ArrayList<PD.Response>();

            ArrayList<String> commentId = PD.Comment.getCommentId(articleId);
            for (int i = 0; i < commentId.size(); i++) {
                response2 = PD.Response.find(commentId.get(i));
                response1.addAll(response1.size(), response2);
//            response=PD.Response.find(commentId.get(i));
//            responses.add(response);
            }

            all = new RT_All(1,PD.Comment.find(articleId), response1);
//        }
        Base.terminate();
        return all;
    }
}

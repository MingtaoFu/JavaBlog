package Service;

import DA.Base;
import PD.*;
import PD.Article;
import PD.Comment;
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
        if(commentId==null){
            return new RT_All(3);
        }
        for (int i = 0; i < commentId.size(); i++) {
            response2 = PD.Response.find(commentId.get(i));
            if(i==0&&response2.get(0).getFromUser().equals("$")&&
                    response2.get(0).getToUser().equals("$")){
                return new RT_All(2);
            }
            response1.addAll(response1.size(), response2);
//            response=PD.Response.find(commentId.get(i));
//            responses.add(response);
        }
        ArrayList<PD.Comment>c=PD.Comment.find(articleId);
        if (c.get(0).getUser().equals("$")){
            return new RT_All(2);
        }
        all = new RT_All(1,c,response1);
//        }
        Base.terminate();
        return all;
    }
}

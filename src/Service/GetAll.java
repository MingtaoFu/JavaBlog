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
    public RT_All getAll(@QueryParam("articleId") int articleId
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

//        ArrayList<String> commentId = PD.Comment.getCommentId(articleId);
        ArrayList<PD.Comment> comment = PD.Comment.find(articleId);

        if(comment != null) {
            if (comment.get(0).getUser().equals("$")) {
                Base.terminate();
                return new RT_All(2);
            }
            for (int i = 0; i < comment.size(); i++) {
                response2 = PD.Response.find(comment.get(i).getId());
                if (response2 != null) {
                    if (i == 0 && response2.get(0).getFromUser().equals("$") &&
                            response2.get(0).getToUser().equals("$")) {
                        Base.terminate();
                        return new RT_All(2);
                    }
                    response1.addAll(response1.size(), response2);
                    //            response=PD.Response.find(commentId.get(i));
                    //            responses.add(response);
                }
            }
        }
        all = new RT_All(1,comment,response1);
//        }
        Base.terminate();
        return all;
    }
}

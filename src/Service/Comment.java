package Service;


import DA.Base;
import PD.User;

import javax.print.attribute.standard.MediaTray;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

/**
 * Created by pc on 2015/11/8.
 */
@Path("api/article/comment")
public class Comment {
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)

    public RT_AddComment addComment(@FormParam("content") String content,
                                    @FormParam("articleId") String articleId,
                                    @CookieParam("id") String id,
                                    @CookieParam("token") String token){
        Base.initialize();
        if(token == null || id == null){
            return new RT_AddComment(0);
        }
        User currentUser=User.validate(id, token);
        RT_AddComment addComment;
        if(currentUser == null){
            addComment=new RT_AddComment(2);
        }
        else{
            PD.Comment comment=new PD.Comment(articleId,content,currentUser.getId());
            comment.add();
            addComment=new RT_AddComment(1);
        }
        Base.terminate();
        return addComment;
    }

    @POST
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)//删除Comment时会把其所有Response也删除！
    public RT_DeleteComment deleteComment(@FormParam("id") String commentId,
                                          @CookieParam("id") String id,
                                          @CookieParam("token") String token){
        Base.initialize();
        if(token == null || id == null){
            return new RT_DeleteComment(0);
        }
        User currentUser=User.validate(id, token);
        RT_DeleteComment deleteComment;
        if(currentUser == null){
            deleteComment=new RT_DeleteComment(2);
        }
        else{
            PD.Comment.delete(commentId);
            deleteComment=new RT_DeleteComment(1);
        }
        return deleteComment;
    }
//
//    @POST
//    @Path("getAll")
//    @Produces(MediaType.APPLICATION_JSON)
//    public RT_GetAllComment getAllComment(@FormParam("articleId")String articleId,
//                                          @CookieParam("id")String id,
//                                          @CookieParam("token")String token){
//        Base.initialize();
//        if(token == null || id == null){
//            return new RT_GetAllComment(0);
//        }
//        User currentUser=User.validate(id, token);
//        RT_GetAllComment getAllComment;
//        if(currentUser == null){
//            getAllComment=new RT_GetAllComment(2);
//        }
//        else{
//            getAllComment=new RT_GetAllComment(1,PD.Comment.find(articleId));
//        }
//        return getAllComment;
//    }

}

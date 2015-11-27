package Service;

import DA.Base;
import DA.PraiseDA;
import PD.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by zouyingtian on 15/11/27.
 */


/*
    status 0 用户未登陆或数据库错误或未知错误
           1 normal用户点赞成功
           2 normal用户取消赞
           3 用户权限为root

           count返回点赞总数.
 */
@Path("api/article/praise")
public class Praise {
    //by Mingtao Fu
    @GET
    @Path("isStar")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_Base isStar(@QueryParam("articleId") int articleId,
                                     @CookieParam("id") String id,
                                    @CookieParam("token") String token) {
        Base.initialize();
        User currentUser = User.validate(id, token);
        if (currentUser == null)
            return new RT_Base(0);
        else {
            boolean bool = PraiseDA.iStar(articleId, currentUser.getId());
            Base.terminate();
            if(bool) {
                return new RT_Base(1);
            } else {
                return new RT_Base(0);
            }
        }
    }

    //点赞
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_AddPraise addResponse(@FormParam("articleId") int articleId,
                                    @CookieParam("id") String id,
                                    @CookieParam("token") String token) {
        if (token == null || id == null) {
            return new RT_AddPraise(0, 0, 0);
        }
        Base.initialize();
        User currentUser = User.validate(id, token);
        RT_AddPraise addPraise;
        if (currentUser != null && currentUser.getType().equals("normal")) {
            PD.Praise praise = new PD.Praise(articleId, currentUser.getId());

            //邹应天此处强势出一波bug
            int status = praise.isPraised();
            if(status==1) {
                addPraise = new RT_AddPraise(1, articleId, praise.getCount(articleId));
                return addPraise;
            }else if (status==2){
                addPraise = new RT_AddPraise(2,articleId,praise.getCount(articleId));
                return addPraise;
            }
            else{
                addPraise = new RT_AddPraise(0, 0, 0);
                return addPraise;
            }

        } else if (currentUser != null && currentUser.getType().equals("root")) {
            addPraise = new RT_AddPraise(3, 0, 0);
            return addPraise;
        }
        addPraise = new RT_AddPraise(0, 0, 0);
        Base.terminate();
        return addPraise;
    }
}
    //取消赞
//    @POST
//    @Path("delete")
//    @Produces(MediaType.APPLICATION_JSON)
//    public RT_DeletePraise deletePraise(@FormParam("articleId") int articleId,
//                                        @CookieParam("id") String id,
//                                        @CookieParam("token") String token){
//        if(token == null || id == null){
//            return new RT_DeletePraise(0,0,0);
//        }
//        Base.initialize();
//        User currentUser=User.validate(id, token);
//        RT_DeletePraise deletePraise;
//        if(currentUser.getType().equals("normal") && currentUser!=null){
//            PD.Praise praise=new PD.Praise(articleId,currentUser.getId());
//            praise.delete(articleId);
//            deletePraise = new RT_DeletePraise(1,articleId,praise.getCount(articleId));
//            return  deletePraise;
//        }
//        else if(currentUser.getType().equals("root") && currentUser!=null){
//            deletePraise= new RT_DeletePraise(2,0,0);
//            return deletePraise;
//        }
//        deletePraise = new RT_DeletePraise(0,0,0);
//        Base.terminate();
//        return deletePraise;
//    }
//}

package Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import DA.Base;
import PD.User;
/**
 * Created by pc on 2015/11/14.
 */
@Path("api/article/comment/Response")
public class Response {
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_AddResponse addResponse(@FormParam("content") String content,
                                      @FormParam("commentId") String commentId,
                                      @FormParam("fromUser") String fromUser,
                                      @FormParam("toUser") String toUser,
                                      @CookieParam("id") String id,
                                      @CookieParam("token") String token){
        Base.initialize();
        if(token == null || id == null){
            return new RT_AddResponse(0);
        }
        User currentUser=User.validate(id, token);
        RT_AddResponse addResponse;
        if(currentUser == null){
            addResponse=new RT_AddResponse(2);
        }
        else{
            PD.Response response=new PD.Response(content,commentId,fromUser,toUser);
            response.add();
            addResponse=new RT_AddResponse(1);
        }
        Base.terminate();
        return addResponse;
    }
    @POST
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_DeleteResponse deleteResponse(@FormParam("id") String responseId,
                                            @CookieParam("id") String id,
                                            @CookieParam("token") String token){
        Base.initialize();
        if(token == null || id == null){
            return new RT_DeleteResponse(0);
        }
        User currentUser=User.validate(id, token);
        RT_DeleteResponse deleteResponse;
        if(currentUser == null){
            deleteResponse=new RT_DeleteResponse(2);
        }
        else{
            PD.Response.delete(responseId);
            deleteResponse=new RT_DeleteResponse(1);
        }
        return deleteResponse;
    }
}

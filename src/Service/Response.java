package Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import DA.Base;
import PD.User;
/**
 * Created by pc on 2015/11/14.
 */
@Path("api/article/comment/response")
public class Response {
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_AddResponse addResponse(@FormParam("content") String content,
                                      @FormParam("commentId") String commentId,
                                      @FormParam("toUser") String toUser,
                                      @CookieParam("id") String id,
                                      @CookieParam("token") String token){
        if(token == null || id == null){
            return new RT_AddResponse(0);
        }
        Base.initialize();
        User currentUser=User.validate(id, token);
        RT_AddResponse addResponse;
        if(currentUser == null){
            addResponse=new RT_AddResponse(3);
        }
        else if(User.find(toUser)==null){
            addResponse=new RT_AddResponse(4);
        }
        else{
            PD.Response response=new PD.Response(content,commentId,id,toUser);
            addResponse=new RT_AddResponse(response.add());
        }
        Base.terminate();
        return addResponse;
    }

    @POST
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_DeleteResponse deleteResponse(@FormParam("responseId") int responseId,
                                            @CookieParam("id") String id,
                                            @CookieParam("token") String token){
        if(token == null || id == null){
            return new RT_DeleteResponse(0);
        }
        Base.initialize();
        User currentUser=User.validate(id, token);
        RT_DeleteResponse deleteResponse;
        if(currentUser == null){
            deleteResponse=new RT_DeleteResponse(3);
        }
        else{
            deleteResponse=new RT_DeleteResponse(PD.Response.delete(responseId));
        }
        return deleteResponse;
    }
}

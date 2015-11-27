package Service;

import DA.Base;
import PD.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by pc on 2015/11/27.
 */
@Path("api/message")
public class Message {
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)

    public RT_AddMessage addMessage(@FormParam("content") String content,
                                    @CookieParam("id") String id,
                                    @CookieParam("token") String token) {
        if (token == null || id == null) {
            return new RT_AddMessage(0);
        }
        Base.initialize();
        User currentUser = User.validate(id, token);
        RT_AddMessage addMessage;
        if (currentUser == null) {
            addMessage = new RT_AddMessage(3);
        } else {
            PD.Message Message = new PD.Message(content, id);
            addMessage = new RT_AddMessage(Message.add());
        }
        Base.terminate();
        return addMessage;
    }

    @POST
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_DeleteMessage deleteMessage(@FormParam("MessageId") int MessageId,
                                          @CookieParam("id") String id,
                                          @CookieParam("token") String token) {
        if (token == null || id == null) {
            return new RT_DeleteMessage(0);
        }
        Base.initialize();
        User currentUser = User.validate(id, token);
        RT_DeleteMessage deleteMessage;
        if (currentUser == null) {
            deleteMessage = new RT_DeleteMessage(3);
        } else {
            deleteMessage = new RT_DeleteMessage(PD.Message.delete(MessageId));
        }
        return deleteMessage;
    }
    @GET
    @Path("getAllMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_GetAllMessage getAllMessage(){
        Base.initialize();
        RT_GetAllMessage getAllMessage=null;
        ArrayList<PD.Message> messages= PD.Message.find();
        if(messages!=null){
            if (messages.get(0).getUserId().equals("$")){
                Base.terminate();
                return new RT_GetAllMessage(2);
            }
            else {
                getAllMessage = new RT_GetAllMessage(1,messages);
            }
        }
        Base.terminate();
        return getAllMessage;
    }
}
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
 * 账号模块
 */

@Path("api/account")
public class Account {
    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_Register register(@FormParam("id") String id,
                         @FormParam("name") String name,
                         @FormParam("pwd") String pwd) {
        Base.initialize();
        User user = new User(id, name, pwd, "normal", null, new Timestamp(System.currentTimeMillis()), null);
        int status = User.add(user);
        RT_Register register;
        if(1 == status) {
            register = new RT_Register(1, user.generateToken());
        } else {
            register = new RT_Register(status, null);
        }

        Base.terminate();
        return register;
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_Login login(@FormParam("id") String id,
                          @FormParam("pwd") String pwd) {
        Base.initialize();
        User user = User.find(id);
        Base.terminate();
        if(user != null) {
            if(user.getPwd().equals(pwd)) {
                return new RT_Login(1, user.generateToken());
            }
        }
        return new RT_Login(0, null);
    }

    @GET
    @Path("personalInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_PersonalInfo personalInfo(@CookieParam("id") String id,
                             @CookieParam("token") String token) {
        Base.initialize();
        User user = User.validate(id, token);
        Base.terminate();
        if(user != null) {
            return new RT_PersonalInfo(1, user);
        } else {
            return new RT_PersonalInfo(0, null);
        }
    }

}

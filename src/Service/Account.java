package Service;

import javax.ws.rs.*;

import DA.Base;
import DA.JedisDA;
import PD.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
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
        //一定要加上 null 判断，否则在 jedis 会出问题
        if(id == null || token == null) {
            return new RT_PersonalInfo(0, null);
        }
        Base.initialize();
        User user = User.validate(id, token);
        Base.terminate();
        if(user != null) {
            return new RT_PersonalInfo(1, user);
        } else {
            return new RT_PersonalInfo(0, null);
        }
    }

    @POST
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_Logout logout(@CookieParam("id") String id,
                             @CookieParam("token") String token) {
        JedisDA.initialize();
        if(token.equals(JedisDA.find(id))) {
            JedisDA.del(id);
        }
        return new RT_Logout(1);
    }

    //修改密码
    @POST
    @Path("modifyPwd")
    @Produces(MediaType.APPLICATION_JSON)
    public RT_Base modifyPwd(@FormParam("oldPwd") String oldPwd,
                             @FormParam("newPwd") String newPwd,
                             @CookieParam("id") String id,
                             @CookieParam("token") String token) {
        JedisDA.initialize();
        Base.initialize();
        RT_Base rt_base;
        User user = User.validate(id, token);
        if(user != null) {
            if(user.getPwd().equals(oldPwd)) {
                if(user.modifyPwd(newPwd)) {
                    rt_base = new RT_Base(1);           //成功
                } else {
                    rt_base = new RT_Base(2);           //SQL错误
                }
            } else {
                rt_base = new RT_Base(3);           //旧密码错误
            }
        } else {
            rt_base = new RT_Base(0);           //身份验证错误
        }
        Base.terminate();
        return rt_base;
    }
}


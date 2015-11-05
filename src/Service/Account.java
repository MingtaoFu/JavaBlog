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

/**
 * 账号模块
 */

@Path("api/account")
public class Account {
    public static void main(String[] args) {
        System.out.println("xxxxxxxxxxxxx");

    }

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User sayHi() {
        /**
         * 如果写成User mingtao = new User("U201417210", "mingtao", "password", "normal",
         * "a student", new Date(),"deault");
         * 就会出错，我不知道为什么
         */

        User mingtao = new User();
        mingtao.setId("U201417210");
        mingtao.setName("mingtao");
        mingtao.setPwd("password");
        mingtao.setType("normal");
        mingtao.setIntro("a student");
        mingtao.setDate(new Timestamp(System.currentTimeMillis()));
        mingtao.setLogoUrl("default");
        return mingtao;
    }


}

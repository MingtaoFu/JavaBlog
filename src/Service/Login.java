package Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import PD.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * 示例
 */
@Path("api/greeting")
public class Login {
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
        mingtao.setDate(new Date());
        mingtao.setLogoUrl("default");
        return mingtao;
    }
}


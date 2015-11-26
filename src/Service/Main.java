package Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import DA.UserDA;
import DA.UserDA.*;

/**
 * Created by mingtao on 11/26/15.
 */
@Path("mainInfo")
public class Main {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PD.User RT_Base() {
        PD.User user;
        DA.Base.initialize();
        user = UserDA.getMain();
        DA.Base.terminate();
        return user;
    }
}

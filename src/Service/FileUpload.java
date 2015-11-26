package Service;
import DA.Base;
import PD.User;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.*;


/**
 * Created by mingtao on 11/25/15.
 */
@Path("/api/file")
public class FileUpload {
    private static final String SERVER_UPLOAD_LOCATION_FOLDER = "./";

    @POST
    @Path("/uploadHead")
    //@Produces("image/*")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public RT_Base uploadFile(
            @CookieParam("id") String id,
            @CookieParam("token") String token,
            @FormDataParam("file") InputStream fileInput,
            @Context ServletContext app) {

        Base.initialize();
        User user = User.validate(id, token);
        Base.terminate();

        System.out.println(app.getRealPath("img"));
        if(null == user) {
            return new RT_Base(0);

        } else {
            String saveLocation = app.getRealPath("/") + "../../../web/img/head_" + user.getId();
            System.out.println(saveLocation);
            saveFile(fileInput, saveLocation);
            return new RT_Base(1);
        }
    }

    private void saveFile(InputStream uploadedInputStream,
                          String serverLocation) {
        try {
            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

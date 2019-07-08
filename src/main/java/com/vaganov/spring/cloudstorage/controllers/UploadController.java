package com.vaganov.spring.cloudstorage.controllers;



import com.vaganov.spring.cloudstorage.entities.CloudFile;
import com.vaganov.spring.cloudstorage.services.CloudFileServices;
import com.vaganov.spring.cloudstorage.services.UserService;
import com.vaganov.spring.cloudstorage.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping
public class UploadController {

    private UserService userService;
    private CloudFileServices cloudFileServices;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setCloudFileServices(CloudFileServices cloudFileServices) {
        this.cloudFileServices = cloudFileServices;
    }


    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest, Principal principal) {// имена параметров (тут - "file") - из формы JSP.

        System.out.println("Bigining.................................");
//        System.out.println("Prinipal" + principal);
//        System.out.println("Prinipal.getName()" + principal.getName());
        System.out.println("Bigining.................................");

        User user = userService.findByUserName(principal.getName());
        String name = null;


        if (!file.isEmpty()) {
            try {
                CloudFile cloudFile = new CloudFile();

                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();
                System.out.println(name);
                String rootPath = "C:\\path\\";  //try also "C:\path\"
                File dir = new File(rootPath + File.separator);


                if (!dir.exists()) {
                    dir.mkdirs();
                }



                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
                //cloudFile.setPath(dir.getAbsolutePath() + File.separator + name);
                cloudFile.setPath(httpServletRequest.getContentType());
                cloudFile.setCreatedAt(LocalDateTime.now());
                cloudFile.setTitle(name);
                cloudFile.setUser(user);
                cloudFileServices.saveFile(cloudFile);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                logger.info("uploaded: " + uploadedFile.getAbsolutePath());

                //return "You successfully uploaded file=" + name;
                //return "up";

                String referrer = httpServletRequest.getHeader("referer");
                return "Файл успешно загружен. Нажмите кнопку назад";

            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

}
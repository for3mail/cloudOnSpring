package com.vaganov.spring.cloudstorage.controllers;

import com.vaganov.spring.cloudstorage.entities.User;
import com.vaganov.spring.cloudstorage.entities.CloudFile;
import com.vaganov.spring.cloudstorage.services.CloudFileServices;
import com.vaganov.spring.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cloud")
public class MainController {
    private CloudFileServices cloudFileServices;
    private UserService userService;

    @Autowired
    public void setCloudFileServices(CloudFileServices cloudFileServices) {
        this.cloudFileServices = cloudFileServices;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String cloudPage(Model model, Principal principal) {
        User user = userService.findByUserName(principal.getName());

        //CloudFile cloudFile = new CloudFile();
        //List<CloudFile> userfileslist = cloudFileServices.findAll();
        List<CloudFile> userfileslist = cloudFileServices.findByUserId(user.getId());
        model.addAttribute("userfileslist", userfileslist);
        //model.addAttribute("cloudFile", cloudFile);
        return "cloud-page";
    }

//    @GetMapping()
//    public String cloudPage(Model model, HttpSession httpSession) {
//        List<CloudFile> userfileslist = cloudFileServices.findAll();
//        model.addAttribute("userfileslist", userfileslist);
//        return "cloud-page";
//    }

//    private static final Logger logger = LoggerFactory.getLogger(CloudFileServices.class);
//
//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    @ResponseBody
//    public String uploadFile(@RequestParam("file") MultipartFile file) {// имена параметров (тут - "file") - из формы JSP.
//        System.out.println("qqqqqqq");
//
//        String name = null;
//
//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//
//                name = file.getOriginalFilename();
//
//                String rootPath = "C:\\path\\";  //try also "C:\path\"
//                File dir = new File(rootPath + File.separator + "loadFiles");
//
//                if (!dir.exists()) {
//                    dir.mkdirs();
//                }
//
//                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
//
//                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
//                stream.write(bytes);
//                stream.flush();
//                stream.close();
//
//                logger.info("uploaded: " + uploadedFile.getAbsolutePath());
//
//                return "You successfully uploaded file=" + name + "<a class=\"nav-link\" th:href=\"@{/shop}\">Каталог товаров<span class=\"sr-only\">(current)</span></a>";
//
//            } catch (Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
//            }
//        } else {
//            return "You failed to upload " + name + " because the file was empty.";
//        }
//    }
}
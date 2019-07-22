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
}

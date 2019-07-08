package com.vaganov.spring.cloudstorage.controllers;



import com.vaganov.spring.cloudstorage.entities.CloudFile;
import com.vaganov.spring.cloudstorage.services.CloudFileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping()
public class DownloadController {

    private CloudFileServices cloudFileServices;

    @Autowired
    public void setCloudFileServices(CloudFileServices cloudFileServices) {
        this.cloudFileServices = cloudFileServices;
    }

    @RequestMapping("download/{id}")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("id") Long id) {

        String serverStoragePath = "C:\\path\\";
        CloudFile cloudFile = cloudFileServices.findById(id);
        String fileName = cloudFile.getTitle();
        String contentType = cloudFile.getPath();
        Path file = Paths.get(serverStoragePath, fileName);

        if (Files.exists(file)) {
            response.setContentType(contentType);
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("******** File not found *********** ");
        }
    }

//    @GetMapping("download/{id}")
//    public String addProductToCart(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
//        System.out.println("*******************download method ******************************************");
////        shoppingCartService.addToCart(httpServletRequest.getSession(), id);
//        String referrer = httpServletRequest.getHeader("referer");
//        return "redirect:" + referrer;
//    }
}

package com.vaganov.spring.cloudstorage.services;




import com.vaganov.spring.cloudstorage.entities.CloudFile;
import com.vaganov.spring.cloudstorage.repositories.CloudFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudFileServices {
    private CloudFileRepository cloudFileRepository;

    @Autowired
    public void setCloudFileRepository(CloudFileRepository cloudFileRepository) {
        this.cloudFileRepository = cloudFileRepository;
    }

    public void saveFile(CloudFile cloudFile){
        CloudFile cloudFileOut = cloudFileRepository.save(cloudFile);
        //cloudFileOut.setConfirmed(true);
    }

    public List<CloudFile> findByUserId(Long id){
        return cloudFileRepository.findAllByUserId(id);
    }

    public CloudFile findById(Long id){
        return cloudFileRepository.findById(id).get();
    }

    public List<CloudFile> findAll(){
        return (List) cloudFileRepository.findAll();
    }

//    public String findTitleById(Long id){
//        return cloudFileRepository.findById(id).get().getTitle();
//    }
}

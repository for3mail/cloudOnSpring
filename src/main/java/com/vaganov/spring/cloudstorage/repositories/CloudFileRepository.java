package com.vaganov.spring.cloudstorage.repositories;




import com.vaganov.spring.cloudstorage.entities.CloudFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CloudFileRepository extends CrudRepository<CloudFile, Long> {
    List<CloudFile> findAllByUserId(Long userId);


}

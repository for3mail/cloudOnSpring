package com.vaganov.spring.cloudstorage.repositories;


import com.vaganov.spring.cloudstorage.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUserName(String userName);
}

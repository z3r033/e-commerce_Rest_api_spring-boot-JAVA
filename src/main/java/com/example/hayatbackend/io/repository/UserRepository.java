/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.io.repository;


import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.hayatbackend.io.entity.UserEntity;


/**
 *
 * @author hayat
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
    UserEntity findUserByEmail (String email );
  
    UserEntity findByUserId (String userId );
    List<UserEntity> findByIdNotIn(Collection<Long> users);
   @Query(value="SELECT * FROM users u WHERE u.user_id !=:utilisateur_id",nativeQuery = true)
    public List<UserEntity> fetchall(String utilisateur_id);


 
}

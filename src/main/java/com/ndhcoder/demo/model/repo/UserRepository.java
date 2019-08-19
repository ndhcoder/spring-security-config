package com.ndhcoder.demo.model.repo;


import com.ndhcoder.demo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getByUserName(String query);
}

package com.example.ShopDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ShopDemo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	@Query("select u from UserEntity u where u.username = :uname")
	UserEntity getbyUsername(@Param("uname") String username);
	@Query("select u from UserEntity u where u.username = :uname and u.password = :upass")
	UserEntity getLogin(@Param("uname") String username, @Param("upass") String password);
}

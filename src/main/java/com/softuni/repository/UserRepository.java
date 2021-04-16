package com.softuni.repository;

import com.softuni.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select u.username from User u order by u.username")
    List<String> findAllUsernames();

    Optional<User> findByUsername(String name);

    Optional<User> findByUsernameAndPassword(String username, String password);


}

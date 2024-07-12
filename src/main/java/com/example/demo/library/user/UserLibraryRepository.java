package com.example.demo.library.user;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLibraryRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByName(String name);
    Optional<UserEntity> findByEmail(String email);
    
}

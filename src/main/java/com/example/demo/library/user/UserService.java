package com.example.demo.library.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    List<UserDto> getUsers() {
        List<UserEntity> users = this.userRepository.findAll();
        return getDtos(users);
    }

    UserDto getUserById(Long id) {
        UserEntity user = this.userRepository.findById(id).orElse(null);
        return UserDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).age(user.getAge())
                .build();
    }

    ArrayList<UserDto> getDtos(List<UserEntity> users) {
        ArrayList<UserDto> userDtos = new ArrayList<UserDto>();
        for (UserEntity userEntity : users) {
            userDtos.add(UserDto.builder()
                    .id(userEntity.getId())
                    .name(userEntity.getName())
                    .email(userEntity.getEmail())
                    .age(userEntity.getAge())
                    .build());
        }
        return userDtos;
    }
}

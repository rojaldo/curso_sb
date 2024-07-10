package com.example.demo.library.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return getDto(user);
    }

    Optional<UserDto> createUser(UserDto userDto) {
        UserEntity userEntity = UserEntity.builder().name(userDto.getName()).email(userDto.getEmail())
                .age(userDto.getAge()).build();
        // check if email user already exists
        if (this.userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            return Optional.empty();
        }
        Optional<UserDto> response;
        try {
            response = Optional.of(getDto(this.userRepository.save(userEntity)));
        } catch (Exception e) {
            return Optional.empty();
        }
        if (response.isEmpty()) {
            return Optional.empty();
        }
        return response;
    }

    Optional<UserDto> updateUser(Long id, UserDto userDto) {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);
        if (userEntity.isEmpty()) {
            return Optional.empty();
        }
        UserEntity user = userEntity.get();
        user.setId(userEntity.get().getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        try {
            return Optional.of(getDto(this.userRepository.save(user)));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    Optional<UserDto> deleteUser(Long id) {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);
        if (userEntity.isEmpty()) {
            return Optional.empty();
        }
        this.userRepository.delete(userEntity.get());
        return Optional.of(getDto(userEntity.get()));
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

    UserDto getDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .age(userEntity.getAge())
                .build();
    }
}

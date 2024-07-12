package com.example.demo.library.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.errors.ErrorDto;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserLibraryRepository userRepository;

    List<IUserResponse> getUsers() {
        List<UserEntity> users = this.userRepository.findAll();
        return getDtos(users);
    }

    public IUserResponse getUserById(Long id) {
        UserEntity user = this.userRepository.findById(id).orElse(null);
        return getDto(user);
    }

    @Transactional
    Optional<IUserResponse> createUser(UserDto userDto) {
        UserEntity userEntity = UserEntity.builder().name(userDto.getName()).email(userDto.getEmail())
                .age(userDto.getAge()).build();
        // check if email user already exists
        if (this.userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            return Optional.of(ErrorDto.builder().code(400).message("User email already exists").build());
        }
        Optional<IUserResponse> response;
        try {
            response = Optional.of(getDto(this.userRepository.save(userEntity)));
        } catch (Exception e) {
            return Optional.of(ErrorDto.builder().code(400).message("User not created").build());
        }
        if (response.isEmpty()) {
            return Optional.of(ErrorDto.builder().code(400).message("User not created").build());
        }
        return response;
    }

    @Transactional
    Optional<IUserResponse> updateUser(Long id, UserDto userDto) {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);
        if (userEntity.isEmpty()) {
            return Optional.of(ErrorDto.builder().code(404).message("User not found").build());
        }
        UserEntity user = userEntity.get();
        user.setId(userEntity.get().getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        try {
            return Optional.of(getDto(this.userRepository.save(user)));
        } catch (Exception e) {
            return Optional.of(ErrorDto.builder().code(400).message("User not updated").build());
        }
    }

    @Transactional
    Optional<IUserResponse> deleteUser(Long id) {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);
        if (userEntity.isEmpty()) {
            return Optional.of(ErrorDto.builder().code(404).message("User not found").build());
        }
        this.userRepository.delete(userEntity.get());
        return Optional.of(getDto(userEntity.get()));
    }

    ArrayList<IUserResponse> getDtos(List<UserEntity> users) {
        ArrayList<IUserResponse> userDtos = new ArrayList<IUserResponse>();
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

    IUserResponse getDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .age(userEntity.getAge())
                .build();
    }

    public void initUsers() {
        UserEntity user1 = UserEntity.
        builder().
        name("John Doe").
        email("email@email.com").
        age(30)
        .address(
                UserAddress.builder().city("City").country("Country").state("State").street("Street").zip("Zip").build())
                .build();
        this.userRepository.save(user1);
    }
}

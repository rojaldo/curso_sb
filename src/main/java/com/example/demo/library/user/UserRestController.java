package com.example.demo.library.user;

import java.util.Map;
import java.util.Optional;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/library")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Map> getMethodName() {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("users", this.userService.getUsers()));
    }

    @PostMapping("/users")
    public ResponseEntity<Map> getMethodName(@RequestBody @Validated UserDto userDto) {
        Optional<UserDto> user = this.userService.createUser(userDto);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "User not created: " + userDto));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("user", user.get()));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Map> getMethodName(@PathVariable Long id, @RequestBody @Validated UserDto userDto) {
        Optional<UserDto> user = this.userService.updateUser(id, userDto);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "User not updated: " + userDto));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("user", user.get()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map> getMethodName(@PathVariable Long id) {
        if (this.userService.deleteUser(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found: " + id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "User deleted: " + id));
    }

    


    
}

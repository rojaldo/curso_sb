package com.example.demo.library.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.errors.ErrorDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public ResponseEntity<List<IUserResponse>> getMethodName() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<IUserResponse> getMethodName(@RequestBody @Validated UserDto userDto) {
        Optional<IUserResponse> user = this.userService.createUser(userDto);
        // check if object returned is UserErrorDto

        if ( user.get() instanceof ErrorDto) {
            // cast to UserErrorDto
            ErrorDto userError = (ErrorDto) user.get();
            return ResponseEntity.status(userError.getCode()).body(userError);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user.get());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<IUserResponse> getMethodName(@PathVariable Long id, @RequestBody @Validated UserDto userDto) {
        Optional<IUserResponse> user = this.userService.updateUser(id, userDto);
        if ( user.get() instanceof ErrorDto ) {
            ErrorDto userError = (ErrorDto) user.get();
            return ResponseEntity.status(userError.getCode()).body(userError);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<IUserResponse> getMethodName(@PathVariable Long id) {
        Optional <IUserResponse> user = this.userService.deleteUser(id);
        if (user.get() instanceof ErrorDto) {
            ErrorDto userError = (ErrorDto) user.get();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userError);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    


    
}

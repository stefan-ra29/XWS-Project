package com.xwsBooking.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String getUsernameById(@PathVariable String id) {
        return userService.getUsernameById(id);
    }

    @PostMapping
    public ResponseEntity<Object> registerUser(@RequestBody RegisterUserDTO user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.err.println("error!");
            Map<String, String> errors = new HashMap<>();
            for (FieldError error:bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        try{
            String username =  userService.registerUser(user);
            if(!username.equals(user.getUsername())){
                //TO DO fali ovde sta da se desi
                return new ResponseEntity<>(username, HttpStatus.BAD_REQUEST);
            }
            else return new ResponseEntity(username, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

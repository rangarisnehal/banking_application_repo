package com.snehal.bankDemo.controllers;

import com.snehal.bankDemo.dtos.UserDto;
import com.snehal.bankDemo.entities.Users;
import com.snehal.bankDemo.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name ="User API's")
public class UsersController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<Users>> getALlUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(Long id){
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/create/normal")
    public ResponseEntity<Users> createNormalUser(@RequestBody UserDto userDto){
        Users user = UserDto.userDtoToUser(userDto);
        return new ResponseEntity<>(userService.createNormalUser(user), HttpStatus.OK);
    }

    @PostMapping("/create/admin")
    public ResponseEntity<Users> createAdminUser(@RequestBody UserDto userDto){
        Users user = UserDto.userDtoToUser(userDto);
        return new ResponseEntity<>(userService.createAdminUser(user), HttpStatus.OK);
    }

}

package com.snehal.bankDemo.services;

import com.snehal.bankDemo.custom_exceptions.UserNotFoundException;
import com.snehal.bankDemo.entities.Users;
import com.snehal.bankDemo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }


    public Users getUser(Long id){
        return usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not available!"));
    }

    public Users createNormalUser(Users user){
        user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public Users createAdminUser(Users user){
        user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }
}

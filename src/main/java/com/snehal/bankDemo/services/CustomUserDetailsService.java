package com.snehal.bankDemo.services;

import com.snehal.bankDemo.custom_exceptions.UserNotFoundException;
import com.snehal.bankDemo.entities.Users;
import com.snehal.bankDemo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not available with the username: "+ username));

        return user;
    }
}

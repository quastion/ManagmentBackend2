package com.opencode.managment.service;

import com.opencode.managment.app.Player;
import com.opencode.managment.app.Response;
import com.opencode.managment.entity.User;
import com.opencode.managment.exception.SuchUserAlreadyUseException;
import com.opencode.managment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private static UserRepository repository;

    @Autowired
    public void setProductRepository(UserRepository repository) {
        this.repository = repository;
    }

    public Response login(User entity) {
        if(repository.fundByUserName(entity.getUserName()) != null){
            throw new SuchUserAlreadyUseException();
        }
        repository.save(entity);

        return new Response("OK");
    }

    public static UserRepository getRepository() {
        return repository;
    }
}

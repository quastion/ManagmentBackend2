package com.opencode.managment.service;

import com.opencode.managment.app.Player;
import com.opencode.managment.app.Response;
import com.opencode.managment.entity.User;
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
    private UserRepository repository;
    private ArrayList<Player> playersOnline;

    {
        playersOnline = new ArrayList<>();
    }

    @Autowired
    public void setProductRepository(UserRepository repository) {
        this.repository = repository;
    }

    public Response login(User entity) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));

        Response response = new Response();
        try{
            repository.save(entity);
            response.setStatus("OK");
            playersOnline.add(new Player(entity));
        } catch(Exception e) {
            response.setStatus("ERROR");
            response.setAnswer("Ошибка авторизации...");
        }

        return response;
    }
}

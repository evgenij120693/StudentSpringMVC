package ru.svetozarov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.svetozarov.models.spring_data.UserRepository;
import ru.svetozarov.services.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgenij on 13.03.2017.
 */
public class MyUserDetails implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    private UserRepository userRepository;

    @Autowired
    public void setUserServiceImp(UserRepository userServiceImp) {
        this.userRepository = userServiceImp;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username);
        List<ru.svetozarov.models.pojo.User> listUser = userRepository.findByLogin(username);
        ru.svetozarov.models.pojo.User myUser = listUser.get(0);//userService.getUserBylogin(username);
        System.out.println(myUser.getLogin() + " this user");
        if (myUser != null) {
            List<GrantedAuthority> list = new ArrayList<>();
            list.add(new SimpleGrantedAuthority(myUser.getRole()));
            UserDetails user = new User(username, myUser.getPassword(), true, true, true, true, list);
            return user;
        } else {
            throw new UsernameNotFoundException(username + " not found");
        }

        /*catch (UserDaoException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(username + " not found");
        }*/
    }


}



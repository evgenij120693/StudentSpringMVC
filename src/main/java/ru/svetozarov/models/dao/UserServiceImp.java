package ru.svetozarov.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.svetozarov.models.pojo.User;
import ru.svetozarov.models.spring_data.UserRepository;


import java.util.List;

/**
 * Created by Evgenij on 21.03.2017.
 */

@Repository
@Transactional
@Service("jpaConService")
public class UserServiceImp implements ru.svetozarov.models.spring_data.UserServiceSpringData {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }
}

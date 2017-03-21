package ru.svetozarov.models.spring_data;

import org.springframework.data.repository.CrudRepository;
import ru.svetozarov.models.pojo.User;

import java.util.List;

/**
 * Created by Evgenij on 21.03.2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByLogin(String login);
    List<User> findByLoginAndPassword(String login, String password);
}

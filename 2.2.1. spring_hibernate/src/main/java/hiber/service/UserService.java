package hiber.service;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    void add(User user);
    User select(String model, int series);

    List<User> listUsers();
}

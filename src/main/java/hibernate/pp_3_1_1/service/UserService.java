package hibernate.pp_3_1_1.service;

import hibernate.pp_3_1_1.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    void saveUser(User user);
    Optional<User> findById(Long id);
    void updateUser(User user);
    void deleteUser(Long id);




}
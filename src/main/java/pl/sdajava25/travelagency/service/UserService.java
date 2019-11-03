package pl.sdajava25.travelagency.service;

import org.springframework.stereotype.Service;
import pl.sdajava25.travelagency.model.User;
import pl.sdajava25.travelagency.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;


    public User addNewUser(Long id, User user) {
        return userRepository.save(user);
    }
}

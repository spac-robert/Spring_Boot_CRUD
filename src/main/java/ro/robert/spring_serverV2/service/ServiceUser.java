package ro.robert.spring_serverV2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.robert.spring_serverV2.entity.User;
import ro.robert.spring_serverV2.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceUser {
    @Autowired
    private final UserRepository userRepository;

    public ServiceUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void add(User user) throws IllegalAccessException {
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());
        if (findUser.isPresent()) {
            throw new IllegalAccessException("email already exist");
        } else {
            userRepository.save(user);
        }
    }

    public void delete(Integer id) throws IllegalAccessException {
        boolean exist = userRepository.existsById(id);
        if (!exist) {
            throw new IllegalAccessException("User doesn't exist");
        } else {
            userRepository.deleteById(id);
        }
    }

    public void updateUser(Integer id, User user) throws IllegalAccessException {
        userRepository.findById(id).map(newUser -> {
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            return userRepository.save(newUser);
        });
    }
}
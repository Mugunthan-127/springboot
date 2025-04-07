package com.example.demo1.service;
import com.example.demo1.entity.User;
import com.example.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public String createUser(User user) {
        if(user.getUsername()==null||user.getUsername().trim().isEmpty())
        return "Username can't be empty";
        if(user.getEmail()==null||user.getEmail().trim().isEmpty())
        {
              return "Email can't be empty";
        }
        if(!(user.getEmail().contains("@gmail.com"))&&!(user.getEmail().contains(".in")))
        {
             return "email is not valid";
        }
        if(user.getPassword().length()<8)
        {
            return "Password must be greater than 8 characters";
        }
        userRepository.save(user);
        return "Added Successfully";        
    }
    public Page<User> getAllUsers(int page, int size, String[] sort) {
        Sort sorting = Sort.by(Sort.Order.by(sort[0]).with(Sort.Direction.fromString(sort[1])));
        Pageable pageable = PageRequest.of(page, size, sorting);
        return userRepository.findAll(pageable);
    }
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
    public List<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public List<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User getUserByPassword(String password) {
        return userRepository.findByPassword(password);
    }
   public String getEmailByUsername(String username) {
        return userRepository.findEmailByUsername(username);
    }
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        }
        return null;
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
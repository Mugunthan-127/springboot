package com.example.demo1.controller;
import com.example.demo1.entity.User;
import com.example.demo1.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "username,asc") String[] sort) {
        return userService.getAllUsers(page, size, sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<User>> getUserByUsername(@PathVariable String username) {
        List<User> user = userService.getUserByUsername(username);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    public List<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
        
    }

    @GetMapping("/UsernameByemail/{username}")
     public ResponseEntity<String> getEmailByUsername(@PathVariable String username) {
        String email = userService.getEmailByUsername(username);
        return email != null ? ResponseEntity.ok(email) : ResponseEntity.notFound().build();
    }

    @GetMapping("password/{password}")
    public ResponseEntity<User> getUserByPassword(@PathVariable String password)
    {
        User user=userService.getUserByPassword(password);
        return user !=null? ResponseEntity.ok(user):ResponseEntity.notFound().build();
    }
    
    

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

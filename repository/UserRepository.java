package com.example.demo1.repository;
import com.example.demo1.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
       @Query("SELECT u FROM User u WHERE u.username = :username")
    List<User>findByUsername(@Param("username") String username);
    @Query("SELECT u FROM User u WHERE u.email LIKE %:email%")
    List<User> findByEmail(@Param("email") String email);
    @Query("Select u FROM User u Where u.password = :password")
    User findByPassword(@Param("password") String password);
    @Query("SELECT u.email FROM User u WHERE u.username = :username")
public String findEmailByUsername(@Param("username") String username);   
}
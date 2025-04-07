package com.example.demo1.repository;
import com.example.demo1.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
      @Query("SELECT o FROM Owner o WHERE o.email = :email")
    Owner findByEmail(@Param("email") String email);
    @Query("SELECT COUNT(o) FROM Owner o WHERE o.name = :name")
    long countOwnersByName(@Param("name") String name);
}
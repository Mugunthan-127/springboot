package com.example.demo1.repository;

import com.example.demo1.entity.Accommodation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
   
    @Query("SELECT a FROM Accommodation a WHERE a.location = :location")
    List<Accommodation> findByLocation(@Param("location") String location);
    
    @Query("SELECT a FROM Accommodation a WHERE a.pricePerNight BETWEEN :minPrice AND :maxPrice")
    List<Accommodation> findAccommodationsWithinPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
    
    @Query("SELECT a FROM Accommodation a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Accommodation> findByKeywordInName(@Param("keyword") String keyword);

    @Query("Select a.pricePerNight From Accommodation a where a.location = :location")
    public double findByPricePerNight(@Param("location") String location);
}

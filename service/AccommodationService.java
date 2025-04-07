package com.example.demo1.service;
import com.example.demo1.entity.Accommodation;
import com.example.demo1.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class AccommodationService {
    @Autowired
    private AccommodationRepository accommodationRepository;
    public Accommodation createAccommodation(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }
    public Page<Accommodation> getAllAccommodations(int page, int size, String[] sort) {
        Sort sorting = Sort.by(Sort.Order.by(sort[0]).with(Sort.Direction.fromString(sort[1])));
        Pageable pageable = PageRequest.of(page, size, sorting);
        return accommodationRepository.findAll(pageable);
    }

   
    public Accommodation getAccommodationById(Long id) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        return accommodation.orElse(null);
    }
    public double getByPricePerNight(String location)
    {
        return accommodationRepository.findByPricePerNight(location);
    }

    public List<Accommodation> getAccommodationsByLocation(String location) {
        return accommodationRepository.findByLocation(location);
    }

    public List<Accommodation> getAccommodationsWithinPriceRange(double minPrice, double maxPrice) {
        return accommodationRepository.findAccommodationsWithinPriceRange(minPrice, maxPrice);
    }

    public List<Accommodation> getAccommodationsByKeywordInName(String keyword) {
        return accommodationRepository.findByKeywordInName(keyword);
    }

    
    public Accommodation updateAccommodation(Long id, Accommodation updatedAccommodation) {
        Optional<Accommodation> existingAccommodation = accommodationRepository.findById(id);
        if (existingAccommodation.isPresent()) {
            Accommodation accommodation = existingAccommodation.get();
          
            accommodation.setLocation(updatedAccommodation.getLocation());
            return accommodationRepository.save(accommodation);
        }
        return null; 
    }


    public void deleteAccommodation(Long id) {
        accommodationRepository.deleteById(id);
    }
}

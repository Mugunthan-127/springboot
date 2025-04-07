package com.example.demo1.service;
import com.example.demo1.entity.Owner;
import com.example.demo1.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    public String createOwner(Owner owner) {
        if((owner.getName()==null||owner.getName().trim().isEmpty())||(owner.getEmail()==null||owner.getEmail().trim().isEmpty()))
        {
            return "Name or Email can't be empty";
        }
         ownerRepository.save(owner);
         return "Added successfully";
    }
    public Page<Owner> getAllOwners(int page, int size, String[] sort) {
        Sort sorting = Sort.by(Sort.Order.by(sort[0]).with(Sort.Direction.fromString(sort[1])));
        Pageable pageable = PageRequest.of(page, size, sorting);
        return ownerRepository.findAll(pageable);
    }

    public Owner getOwnerById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        return owner.orElse(null);
    }


        public Owner getOwnerByEmail(String email) {
            return ownerRepository.findByEmail(email);
        }
    

        public long countOwnersByName(String name) {
            return ownerRepository.countOwnersByName(name);
        }


    public Owner updateOwner(Long id, Owner updatedOwner) {
        Optional<Owner> existingOwner = ownerRepository.findById(id);
        if (existingOwner.isPresent()) {
            Owner owner = existingOwner.get();
      
            owner.setName(updatedOwner.getName());
            owner.setEmail(updatedOwner.getEmail());
            return ownerRepository.save(owner);
        }
        return null; 
    }

   
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }

   
    public void deleteAllOwners() {
        ownerRepository.deleteAll();
    }
}
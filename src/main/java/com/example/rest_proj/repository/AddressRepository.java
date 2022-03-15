package com.example.rest_proj.repository;

import com.example.rest_proj.Entity.AddressEntity;
import com.example.rest_proj.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByUserDetails(UserEntity userDetails);
   Optional<AddressEntity> findByUserDetailsAndAddressId (UserEntity userDetails , String addressId);
}

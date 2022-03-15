package com.example.rest_proj.service.impl;

import com.example.rest_proj.Entity.UserEntity;
import com.example.rest_proj.ExecptionHandling.ErrorMessages;
import com.example.rest_proj.ExecptionHandling.exceptions.AddressServiceExeption;
import com.example.rest_proj.model.DTO.AddressResponseDTO;
import com.example.rest_proj.repository.AddressRepository;
import com.example.rest_proj.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    public List<AddressResponseDTO> getUserAddresses(String userId) {
        ModelMapper mapper = new ModelMapper();

        UserEntity userEntity = userService.getUserEntityByUserId(userId);



        return addressRepository.findAllByUserDetails(userEntity)
                .stream()
                .map(c -> mapper.map(c,AddressResponseDTO.class))
                .collect(Collectors.toList());
    }

    public AddressResponseDTO getUserAddressesByAddressId(String userId, String addressId) {
        UserEntity userEntity = userService.getUserEntityByUserId(userId);
        ModelMapper mapper = new ModelMapper();
        return addressRepository.findByUserDetailsAndAddressId(userEntity,addressId).map(c -> mapper.map(c,AddressResponseDTO.class))
                .orElseThrow(()-> new AddressServiceExeption(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
    }
}

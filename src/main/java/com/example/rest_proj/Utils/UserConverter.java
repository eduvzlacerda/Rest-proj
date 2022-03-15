package com.example.rest_proj.Utils;


import com.example.rest_proj.Entity.UserEntity;
import com.example.rest_proj.model.DTO.AddressDTO;
import com.example.rest_proj.model.DTO.AddressResponseDTO;
import com.example.rest_proj.model.DTO.UserResponseDTO;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserConverter {

    public static UserResponseDTO userEntityToUserResponseDTO(UserEntity user){
        ModelMapper mapper = new ModelMapper();
        List<AddressResponseDTO>  addresses = new ArrayList<>();
        if(user.getAddresses().size()>0) {
            for (int i = 0; i < user.getAddresses().size(); i++) {
                AddressResponseDTO addressDTO = mapper.map(user.getAddresses().get(i), AddressResponseDTO.class);
                addresses.add(addressDTO);
            }
        }
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .addresses(addresses)
                .build();


    }

}

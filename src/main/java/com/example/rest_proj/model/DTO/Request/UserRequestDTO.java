package com.example.rest_proj.model.DTO.Request;

import com.example.rest_proj.model.DTO.Request.AddressRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressRequestDTO> userAddresses;
}

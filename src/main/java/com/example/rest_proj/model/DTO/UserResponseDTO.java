package com.example.rest_proj.model.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserResponseDTO {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AddressResponseDTO> addresses;

}


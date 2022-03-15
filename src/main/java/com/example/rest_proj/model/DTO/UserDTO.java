package com.example.rest_proj.model.DTO;

import com.example.rest_proj.model.DTO.Request.AddressRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus = false;
    private List<AddressDTO> addresses;
}

package com.example.rest_proj.model.DTO.Request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogInRequestDTO {
    private  String email;
    private  String password;
}

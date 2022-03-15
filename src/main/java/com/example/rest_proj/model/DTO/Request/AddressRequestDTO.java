package com.example.rest_proj.model.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressRequestDTO {

    private String city;
    private String country;
    private String street;
    private String type;
}

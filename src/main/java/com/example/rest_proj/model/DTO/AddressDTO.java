package com.example.rest_proj.model.DTO;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class AddressDTO {
    private long id;

    private String addressId;
    private String city;
    private String country;
    private String street;
    private String type;
    private UserDTO userDetails;
}

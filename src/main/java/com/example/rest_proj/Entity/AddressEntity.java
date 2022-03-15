package com.example.rest_proj.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="addresses")
@Getter @Setter
public class AddressEntity implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(length = 30,nullable = false)
    private String addressId;
    @Column(length = 30,nullable = false)
    private String city;
    @Column(length = 30,nullable = false)
    private String country;
    @Column(length = 100,nullable = false)
    private String street;
    @Column(length = 30,nullable = false)
    private String type;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity userDetails;




}

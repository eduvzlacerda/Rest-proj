package com.example.rest_proj.Entity;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "users")
@Getter @Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,length = 25)
    private String userId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

    @Column(nullable = false,length = 50, unique = true)
    private String email;

    @NonNull
    private String password;

    @Column(nullable = false )
    private String encryptedPassword = "test";

    private String emailVerificationToken;
    @NonNull
    private Boolean emailVerificationStatus = false;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<AddressEntity> addresses;



}

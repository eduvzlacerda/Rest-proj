package com.example.rest_proj.service;

import com.example.rest_proj.Entity.UserEntity;
import com.example.rest_proj.model.DTO.UserDTO;
import com.example.rest_proj.model.DTO.Request.UserRequestDTO;
import com.example.rest_proj.model.DTO.UserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;



public interface UserService extends UserDetailsService {



    UserResponseDTO createUser(UserDTO UserDTO);
    UserResponseDTO getUserByEmail(String email);
    List<UserResponseDTO> getUsers();
    UserResponseDTO getUserById(String userId);
    UserResponseDTO updateUser(String id, UserRequestDTO details);
    void deleteUser(String userId);
    List<UserResponseDTO> findUsersFromPage(int page, int limit);

    UserEntity getUserEntityByUserId(String userId);

    boolean verifyEmailToken(String token);
}

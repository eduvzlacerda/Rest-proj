package com.example.rest_proj.service.impl;

import com.example.rest_proj.Entity.UserEntity;
import com.example.rest_proj.ExecptionHandling.ErrorMessages;
import com.example.rest_proj.ExecptionHandling.exceptions.UserServiceException;
import com.example.rest_proj.Utils.UserConverter;
import com.example.rest_proj.model.DTO.AddressDTO;
import com.example.rest_proj.model.DTO.Request.UserRequestDTO;
import com.example.rest_proj.model.DTO.UserResponseDTO;
import com.example.rest_proj.service.Utils.Utils;
import com.example.rest_proj.model.DTO.UserDTO;
import com.example.rest_proj.repository.UserRepository;
import com.example.rest_proj.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private Utils utils;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO createUser(UserDTO userDTO) throws UserServiceException {

        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        for(int i=0;i<userDTO.getAddresses().size();i++){
            AddressDTO address = userDTO.getAddresses().get(i);
            address.setUserDetails(userDTO);
            address.setAddressId(utils.generateAddressId(20));
            userDTO.getAddresses().set(i,address);//update the address on userDTO
        }


        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = modelMapper.map(userDTO,UserEntity.class);

        userEntity.setEncryptedPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setUserId(utils.generateUserId(20));


        UserEntity storedUser =  userRepository.save(userEntity);

        return UserConverter.userEntityToUserResponseDTO(storedUser);

    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(()-> new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
        return UserConverter.userEntityToUserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        ModelMapper mapper = new ModelMapper();
        return userRepository.findAll()
                .stream()
                //.map(c -> mapper.map(c,UserResponseDTO.class))
                .map(UserConverter::userEntityToUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(String userId) {
        UserEntity foundUser = getUserEntityByUserId(userId);
        return UserConverter.userEntityToUserResponseDTO(foundUser);
    }

    @Override
    public UserResponseDTO updateUser(String id, UserRequestDTO details) {
        UserEntity foundUser = getUserEntityByUserId(id);
        if(!foundUser.getEmail().equals(details.getEmail())){
            throw new UserServiceException(ErrorMessages.COULD_NOT_UPDATE_RECORD.getErrorMessage() + "Email cannot be changed");
        }
        foundUser.setFirstName(details.getFirstName());
        foundUser.setLastName(details.getLastName());
        return UserConverter.userEntityToUserResponseDTO(userRepository.save(foundUser));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity user =  userRepository.findByEmail(username).orElseThrow(()->new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
       return new User(user.getEmail(),user.getEncryptedPassword(),new ArrayList<>()) ;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity foundUser = getUserEntityByUserId(userId);
        userRepository.delete(foundUser);
    }

    @Override
    public List<UserResponseDTO> findUsersFromPage(int page, int limit) {
        if(page < 0 | limit < 1 ){
            throw new UserServiceException(ErrorMessages.BAD_REQUEST.getErrorMessage());
        }

        org.springframework.data.domain.Pageable pageable =  PageRequest.of(page,limit);


       return userRepository.findAll(pageable).getContent()
               .stream()
               .map(UserConverter::userEntityToUserResponseDTO)
               .collect(Collectors.toList());
    }

    public UserEntity getUserEntityByUserId(String userId){
        return userRepository.findByUserId(userId).orElseThrow(()->new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
    }

    @Override
    public boolean verifyEmailToken(String token) {
        boolean returnValue = false;
        UserEntity userEntity = userRepository.findByEmailVerificationToken(token).orElseThrow(() -> new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
       if(!Utils.hasTokenExpired(token)){
           userEntity.setEmailVerificationToken(null);
           userEntity.setEmailVerificationStatus(Boolean.TRUE);
           userRepository.save(userEntity);
           return true;
       }

        return false;
    }
}

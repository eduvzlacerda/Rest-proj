package com.example.rest_proj.controller;

import com.example.rest_proj.model.DTO.AddressResponseDTO;
import com.example.rest_proj.model.DTO.OperationStatusModel;
import com.example.rest_proj.model.DTO.UserDTO;
import com.example.rest_proj.model.DTO.UserResponseDTO;
import com.example.rest_proj.model.DTO.Request.UserRequestDTO;
import com.example.rest_proj.service.UserService;
import com.example.rest_proj.service.impl.AddressService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {


    private final UserService userService;

     private final AddressService addressService;

    @GetMapping("/{userId}")
    public UserResponseDTO getUser(@PathVariable String userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> postUser(@RequestBody UserRequestDTO userDetails ){

        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(userDetails,UserDTO.class);

        return ok(userService.createUser(userDTO));
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String userId, @RequestBody UserRequestDTO userDetails){
        return ok(userService.updateUser(userId,userDetails));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsersWithPaging(@RequestParam(name = "page" , defaultValue = "0") int page ,
                                                                    @RequestParam(name = "limit",defaultValue = "2") int limit){
       return ok(userService.findUsersFromPage(page,limit)) ;

    }
    @GetMapping("{userId}/addresses")
    public ResponseEntity<List<AddressResponseDTO>> getUserAddresses(@PathVariable String userId){
        return ok(addressService.getUserAddresses(userId));
    }
    @GetMapping("{userId}/addresses/{addressId}")
    public EntityModel<AddressResponseDTO> getUserAddressByAddressId(@PathVariable String userId , @PathVariable String addressId){

        var userlink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUser(userId))
                .withRel("user");

        var addressesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUserAddresses(userId))
                .withRel("user addresses");

      AddressResponseDTO returnAddress = addressService.getUserAddressesByAddressId(userId,addressId);
      return EntityModel.of(returnAddress, Arrays.asList(userlink,addressesLink)) ;
    }

    @GetMapping("/email-verification")
    public OperationStatusModel verifyEmail(@RequestParam(value = "token") String token){

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName("Verify Email");
        boolean isVerified = userService.verifyEmailToken(token);

        if(isVerified)
            returnValue.setOperationResult("SUCCESS");
        else
            returnValue.setOperationResult("ERROR");

        return returnValue ;

    }


}

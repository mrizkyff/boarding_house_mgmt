package com.boardinghouse.ianboardinghouse.service.impl;

import com.boardinghouse.ianboardinghouse.dto.UserDto;
import com.boardinghouse.ianboardinghouse.model.User;
import com.boardinghouse.ianboardinghouse.repository.UserRepository;
import com.boardinghouse.ianboardinghouse.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public User getUserOr404(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public UserDto getUser(UUID id) {
        User userOr404 = getUserOr404(id);
        return new UserDto(
                userOr404.getId(),
                userOr404.getFirstName(),
                userOr404.getLastName(),
                userOr404.getPlace_of_birth(),
                userOr404.getDate_of_birth(),
                userOr404.getPhone(),
                userOr404.getEmail(),
                userOr404.getAddress(),
                userOr404.getGender(),
                userOr404.getMarital_status(),
                userOr404.getReligion(),
                userOr404.getIdentity_type(),
                userOr404.getIdentity(),
                userOr404.getStatus(),
                userOr404.getRole(),
                userOr404.getPassword()
        );
    }

    @Override
    public User createUser(User user) {
        try{
            User save = userRepository.save(user);
            return getUserOr404(save.getId());
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to create user");
        }
    }

    @Override
    public User updateUser(UUID id , User user) {
        User userOr404 = getUserOr404(id);
        try {
            if (userOr404 != null) {
                userOr404.setFirstName(user.getFirstName());
                userOr404.setLastName(user.getLastName());
                userOr404.setPlace_of_birth(user.getPlace_of_birth());
                userOr404.setDate_of_birth(user.getDate_of_birth());
                userOr404.setPhone(user.getPhone());
                userOr404.setEmail(user.getEmail());
                userOr404.setAddress(user.getAddress());
                userOr404.setGender(user.getGender());
                userOr404.setMarital_status(user.getMarital_status());
                userOr404.setReligion(user.getReligion());
                userOr404.setIdentity_type(user.getIdentity_type());
                userOr404.setIdentity_type(user.getIdentity_type());
                userOr404.setStatus(user.getStatus());
                userOr404.setRole(user.getRole());
                userOr404.setPassword(user.getPassword());
                userRepository.save(userOr404);
            }
            return getUserOr404(id);
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to update user");
        }
    }

    @Override
    public Boolean deleteUser(UUID id) {
        User result = getUserOr404(id);
        if (result != null) {
            userRepository.delete(result);
            return true;
        }
        throw new ResponseStatusException(HttpStatus.BAD_GATEWAY , "Failed to delete user");
    }


    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return users;
        }
        return new ArrayList<>();
    }
}

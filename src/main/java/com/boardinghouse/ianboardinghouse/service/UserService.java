package com.boardinghouse.ianboardinghouse.service;

import com.boardinghouse.ianboardinghouse.dto.UserDto;
import com.boardinghouse.ianboardinghouse.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserOr404(UUID id);
    UserDto getUser(UUID id);
    User createUser(User user);
    User updateUser(UUID id, User user);
    Boolean deleteUser(UUID id);
    List<User> findAll();
}
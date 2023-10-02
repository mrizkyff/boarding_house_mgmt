package com.boardinghouse.ianboardinghouse.controller;

import com.boardinghouse.ianboardinghouse.dto.UserDto;
import com.boardinghouse.ianboardinghouse.model.Room;
import com.boardinghouse.ianboardinghouse.model.User;
import com.boardinghouse.ianboardinghouse.service.RoomService;
import com.boardinghouse.ianboardinghouse.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;

    @PostMapping("")
    public UserDto createUser(@RequestBody @Valid UserDto userDto){
        // get room first
        Room room = roomService.getRoomOr404(userDto.room_id());
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setRoom(room);
        User result = userService.createUser(user);
        return userService.getUser(result.getId());
    }

    @GetMapping("")
    public List<UserDto> getAllUser(){
        List<User> users = userService.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(
                    new UserDto(
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getPlace_of_birth(),
                            user.getDate_of_birth(),
                            user.getPhone(),
                            user.getEmail(),
                            user.getAddress(),
                            user.getGender(),
                            user.getMarital_status(),
                            user.getReligion(),
                            user.getIdentity_type(),
                            user.getIdentity(),
                            user.getStatus(),
                            user.getRole(),
                            user.getPassword(),
                            user.getRoom().getId(),
                            user.getRoom()
                    )
            );
        }
        return userDtos;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable UUID id){
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable UUID id, @RequestBody @Valid UserDto userDto){
        // get room first
        Room room = roomService.getRoomOr404(userDto.room_id());
        User user = new User();
        BeanUtils.copyProperties(userDto, user, "id");
        user.setRoom(room);
        User result = userService.updateUser(id, user);
        return userService.getUser(result.getId());
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable UUID id){
        return userService.deleteUser(id);
    }

}

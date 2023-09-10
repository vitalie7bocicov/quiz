package ro.uaic.fii.UserService.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.fii.UserService.convertor.UserDtoToModel;
import ro.uaic.fii.UserService.dto.UserDto;
import ro.uaic.fii.UserService.model.User;
import ro.uaic.fii.UserService.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> addSession(@Valid @RequestBody UserDto userDto)
    {
        User user = UserDtoToModel.convert(userDto);
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll()
    {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<User> getById(@PathVariable UUID id)
    {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateSession(@PathVariable UUID id,
                                              @Valid @RequestBody UserDto userDto)
    {
        User updatedUser = UserDtoToModel.convert(userDto);
        User user = userService.update(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id)
    {
        userService.deleteById(id);
        return ResponseEntity.ok("User with ID: " + id + " deleted.");
    }
}

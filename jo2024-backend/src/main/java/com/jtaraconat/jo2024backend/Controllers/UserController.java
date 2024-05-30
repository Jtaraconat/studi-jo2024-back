package com.jtaraconat.jo2024backend.Controllers;

import com.jtaraconat.jo2024backend.Exceptions.UserNotFoundException;
import com.jtaraconat.jo2024backend.Models.User;
import com.jtaraconat.jo2024backend.Repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"https://studi-jo2024.web.app"})
@Tag(name ="User API", description = "API for managing users")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/adduser")
    @Operation(method = "POST", summary = "Add a user", description = "Allows an admin to add a new user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/api/user/{id}")
    @Operation(method = "GET", summary = "Get a user", description = "Allows the fontend to get a user found by his userId")
    User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }
}

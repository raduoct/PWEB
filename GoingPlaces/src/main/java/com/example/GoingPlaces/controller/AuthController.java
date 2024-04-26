package com.example.GoingPlaces.controller;

import com.example.GoingPlaces.config.JwtService;
import com.example.GoingPlaces.dto.AuthDto;
import com.example.GoingPlaces.dto.UserDto;
import com.example.GoingPlaces.model.Role;
import com.example.GoingPlaces.model.User;
import com.example.GoingPlaces.repository.UserRepository;
import com.example.GoingPlaces.service.SegmentCalatorieService;
import com.example.GoingPlaces.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.net.ssl.SSLEngineResult;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody UserDto userDto) {
        User user = userService.registerUser(userDto, Role.ROLE_ADMIN);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        User user = userService.registerUser(userDto, Role.ROLE_USER);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/authenticate")
    public String getToken(@RequestBody AuthDto authDto) {
        User user = userRepository.findByEmail(authDto.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

//        if(!passwordEncoder.encode(authDto.getPassword()).equals(user.getPassword()))
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User and password don't match.");

        HashMap<String, Object> claims = new HashMap<>();
//        claims.put("role", user.getRole().name());
        return jwtService.doGenerateToken(claims, user.getEmail(), user.getRole().name());
    }
}
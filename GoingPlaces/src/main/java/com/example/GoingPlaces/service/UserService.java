package com.example.GoingPlaces.service;

import com.example.GoingPlaces.dto.UserDto;
import com.example.GoingPlaces.model.Role;
import com.example.GoingPlaces.model.User;
import com.example.GoingPlaces.repository.TraseuStatieRepository;
import com.example.GoingPlaces.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public User registerUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with email " + userDto.getEmail() + " already exists.");
        }
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    }

    @Transactional
    public User updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
        if (userDto.getEmail() != null && !userDto.getEmail().isEmpty()){
            if(userRepository.findByEmail(userDto.getEmail()).isEmpty() || !userDto.getEmail().equals(user.getEmail()))
                user.setEmail(userDto.getEmail());
            else
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User with email " + userDto.getEmail() + " already exists.");
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad email.");

        if (userDto.getFirstName() != null && !userDto.getFirstName().isEmpty())
            user.setFirstName(userDto.getFirstName());
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad first name.");

        if (userDto.getLastName() != null && !userDto.getLastName().isEmpty())
            user.setLastName(userDto.getLastName());
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad last name.");

        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty())
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad password.");
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
        userRepository.deleteById(id);
    }

    public User registerUser(UserDto userDto, Role role) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with email " + userDto.getEmail() + " already exists.");
        }
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(role);
        return userRepository.save(user);
    }
}
package com.example.GoingPlaces.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name can only contain letters and spaces.")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name can only contain letters and spaces.")
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{10,}$",
            message = "Password must be at least 10 characters long, contain at least one uppercase letter and one digit.")
    private String password;
}

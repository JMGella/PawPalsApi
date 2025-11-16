package com.pawpals.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DogInDTO {

    @NotBlank
    private String name;

    private String breed;
    private LocalDate birthdate;
    private String description;
    private String profileImageUrl;
}

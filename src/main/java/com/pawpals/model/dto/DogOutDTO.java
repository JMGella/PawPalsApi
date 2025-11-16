package com.pawpals.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DogOutDTO {

    private Long id;
    private String name;
    private String breed;
    private LocalDate birthdate;
    private String description;
    private String profileImageUrl;
    private OffsetDateTime createdAt;
    private UserOutDTO owner;
}

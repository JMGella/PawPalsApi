package com.pawpals.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkInDTO {

    @NotNull
    private Long creatorId;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private OffsetDateTime startTime;

    private OffsetDateTime endTime;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private Integer maxDogs;
}
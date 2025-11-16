package com.pawpals.model.dto;

import com.pawpals.model.WalkStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkOutDTO {

    private Long id;
    private String title;
    private String description;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private Double latitude;
    private Double longitude;
    private Integer maxDogs;
    private WalkStatus status;
    private OffsetDateTime createdAt;

    private UserOutDTO creator;
}
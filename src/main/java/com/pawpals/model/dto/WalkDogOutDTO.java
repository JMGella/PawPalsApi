package com.pawpals.model.dto;

import com.pawpals.model.ParticipationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkDogOutDTO {

    private Long id;
    private ParticipationStatus status;
    private OffsetDateTime joinedAt;

    private WalkOutDTO walk;
    private DogOutDTO dog;
    private UserOutDTO handler;
}

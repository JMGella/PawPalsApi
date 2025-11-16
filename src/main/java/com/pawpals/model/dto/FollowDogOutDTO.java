package com.pawpals.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowDogOutDTO {

    private Long id;
    private Long followerUserId;
    private Long dogId;
    private OffsetDateTime createdAt;
}
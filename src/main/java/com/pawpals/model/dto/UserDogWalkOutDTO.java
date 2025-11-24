package com.pawpals.model.dto;

import com.pawpals.model.ParticipationStatus;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserDogWalkOutDTO {

    private Long id;                    
    private DogOutDTO dog;              
    private WalkOutDTO walk;            
    private ParticipationStatus status; 
    private OffsetDateTime joinedAt;
}

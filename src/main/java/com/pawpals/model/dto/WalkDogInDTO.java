package com.pawpals.model.dto;

import com.pawpals.model.ParticipationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkDogInDTO {


    private Long walkId;

    @NotNull
    private Long dogId;

    @NotNull
    private Long handlerId;

    private ParticipationStatus status;
}

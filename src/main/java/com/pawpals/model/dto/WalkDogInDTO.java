package com.pawpals.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkDogInDTO {

    @NotNull
    private Long walkId;

    @NotNull
    private Long dogId;

    @NotNull
    private Long handlerId;
}

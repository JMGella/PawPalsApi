package com.pawpals.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowDogInDTO {

    @NotNull
    private Long followerUserId;
    @NotNull
    private Long dogId;
}

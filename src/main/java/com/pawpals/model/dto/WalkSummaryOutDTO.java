package com.pawpals.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkSummaryOutDTO {

    private WalkOutDTO walk;
    private List<WalkDogOutDTO> participants;
}

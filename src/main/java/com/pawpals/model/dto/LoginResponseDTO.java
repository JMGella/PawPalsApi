package com.pawpals.model.dto;

import com.pawpals.model.dto.UserOutDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String token;
    private UserOutDTO user;
}

package com.pawpals.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutDTO {

    private Long id;
    private String email;
    private String displayName;
    private String username;
    private String profileImageUrl;
    private OffsetDateTime createdAt;

}

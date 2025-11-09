package com.pawpals.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "walks")
public class Walk {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @NotNull
    @Column(name = "start_time", nullable = false, columnDefinition = "timestamptz")
    private OffsetDateTime startTime;

    @Column(name = "end_time", columnDefinition = "timestamptz")
    private OffsetDateTime endTime;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(name = "max_dogs")
    private Integer maxDogs;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WalkStatus status = WalkStatus.SCHEDULED;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

}

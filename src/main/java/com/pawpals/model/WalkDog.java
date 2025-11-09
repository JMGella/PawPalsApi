package com.pawpals.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "walk_dogs")
public class WalkDog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "walk_id")
    private Walk walk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dog_id")
    private Dog dog;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "handler_id")
    private User handler;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParticipationStatus status = ParticipationStatus.JOINED;

    @Column(name = "joined_at", nullable = false)
    private OffsetDateTime joinedAt = OffsetDateTime.now();
}

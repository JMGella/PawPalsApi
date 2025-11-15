package com.pawpals.repository;

import com.pawpals.model.Walk;
import com.pawpals.model.WalkStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface WalkRepository extends JpaRepository<Walk, Long> {

    List<Walk> findByStartTimeAfterAndStatus (OffsetDateTime startTime, WalkStatus status);

    List<Walk> findByCreatorId(Long creatorId);
}
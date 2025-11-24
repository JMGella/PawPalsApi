package com.pawpals.service;

import com.pawpals.model.dto.WalkInDTO;
import com.pawpals.model.dto.WalkOutDTO;
import com.pawpals.model.User;
import com.pawpals.model.Walk;
import com.pawpals.model.WalkStatus;
import com.pawpals.repository.UserRepository;
import com.pawpals.repository.WalkRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@Transactional
public class WalkService {

    private final WalkRepository walkRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public WalkService(WalkRepository walkRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.walkRepository = walkRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public WalkOutDTO createWalk(WalkInDTO in) {
        User creator = userRepository.findById(in.getCreatorId()).orElseThrow(() -> new ResourceNotFoundException("Creator not found"));

        Walk walk = new Walk();
        walk.setCreator(creator);
        walk.setTitle(in.getTitle());
        walk.setDescription(in.getDescription());
        walk.setStartTime(in.getStartTime());
        walk.setEndTime(in.getEndTime());
        walk.setLatitude(in.getLatitude());
        walk.setLongitude(in.getLongitude());
        walk.setMaxDogs(in.getMaxDogs());
        walk.setStatus(WalkStatus.SCHEDULED);

        Walk saved = walkRepository.save(walk);
        return modelMapper.map(saved, WalkOutDTO.class);
    }

    @Transactional(readOnly = true)
    public WalkOutDTO getWalkById(Long id) {
        Walk walk = walkRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Walk not found"));
        return modelMapper.map(walk, WalkOutDTO.class);
    }

    @Transactional(readOnly = true)
    public List<WalkOutDTO> getUpcomingWalks() {
        List<Walk> walks = walkRepository.findByStartTimeAfterAndStatus(OffsetDateTime.now(), WalkStatus.SCHEDULED);
        List<WalkOutDTO> walkOutDTOList = modelMapper.map(walks, new TypeToken<List<WalkOutDTO>>() {}.getType());
        return walkOutDTOList;
    }

    @Transactional(readOnly = true)
    public List<WalkOutDTO> getWalksByCreator(Long creatorId) {
        List<Walk> walks = walkRepository.findByCreatorId(creatorId);
        List<WalkOutDTO> walkOutDTOList = modelMapper.map(walks, new TypeToken<List<WalkOutDTO>>() {}.getType());
        return walkOutDTOList;
    }

    public void cancelWalk(Long walkId) {
        Walk walk = walkRepository.findById(walkId).orElseThrow(() -> new ResourceNotFoundException("Walk not found"));
        walk.setStatus(WalkStatus.CANCELLED);
        walkRepository.save(walk);
    }

    @Transactional
    public WalkOutDTO updateWalk(Long walkId, WalkInDTO body) {

        Walk walk = walkRepository.findById(walkId).orElseThrow(() -> new ResourceNotFoundException("Walk not found"));

        if (body.getTitle() != null) {
            walk.setTitle(body.getTitle());
        }
        if (body.getDescription() != null) {
            walk.setDescription(body.getDescription());
        }
        if (body.getStartTime() != null) {
            walk.setStartTime(body.getStartTime());
        }
        if (body.getEndTime() != null) {
            walk.setEndTime(body.getEndTime());
        }
        if (body.getMaxDogs() != null) {
            walk.setMaxDogs(body.getMaxDogs());
        }
        if (body.getStatus() != null) {
            walk.setStatus(body.getStatus());
        }

        Walk saved = walkRepository.save(walk);
        return modelMapper.map(saved, WalkOutDTO.class);
    }

    @Transactional(readOnly = true)
    public List<WalkOutDTO> getAllWalks() {
        List<Walk> walks = walkRepository.findAll();

        return modelMapper.map(walks, new TypeToken<List<WalkOutDTO>>() {}.getType());
    }

}

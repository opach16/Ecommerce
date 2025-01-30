package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public List<GroupDto> getAllGroups() {
        return groupMapper.mapToGroupDtoList(groupRepository.findAll());
    }

    public GroupDto getGroupById(Long id) throws GroupNotFoundException {
        Group group = groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
        return groupMapper.mapToGroupDto(group);
    }

    public GroupDto addGroup(GroupDto groupDto) {
        Group savedGroup = groupRepository.save(groupMapper.mapToGroupEntity(groupDto));
        return groupMapper.mapToGroupDto(savedGroup);
    }

    public GroupDto updateGroup(GroupDto groupDto) throws GroupNotFoundException {
        Group group = groupRepository.findById(groupDto.getId()).orElseThrow(GroupNotFoundException::new);
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());
        return groupMapper.mapToGroupDto(groupRepository.save(group));
    }

    public void deleteGroup(Long id) throws GroupNotFoundException {
        Group group = groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
        groupRepository.delete(group);
    }
}

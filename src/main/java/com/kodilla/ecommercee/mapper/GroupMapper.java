package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMapper {

    public final ProductMapper productMapper;

    public List<GroupDto> mapToGroupDtoList(List<Group> groups) {
        return groups.stream()
                .map(this::mapToGroupDto)
                .toList();
    }

    public GroupDto mapToGroupDto(Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .products(group.getProducts().stream()
                        .map(productMapper::mapToProductDto)
                        .toList()
                )
                .build();
    }

    public Group mapToGroupEntity(GroupDto groupDto) {
        return Group.builder()
                .name(groupDto.getName())
                .description(groupDto.getDescription())
                .build();
    }
}

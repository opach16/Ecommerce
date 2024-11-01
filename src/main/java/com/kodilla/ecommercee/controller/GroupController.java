package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {

    @GetMapping
    public List<GroupDto> getAllGroups() {
        return new ArrayList<>();
    }

    @GetMapping("/{groupId}")
    public GroupDto getGroupById(@PathVariable Long groupId) {
        return new GroupDto(1L, "testGroup", "testGroupDesc");
    }

    @PostMapping
    public void addGroup(GroupDto groupDto) {

    }

    @PutMapping
    public GroupDto updateGroup(GroupDto groupDto) {
        return new GroupDto(1L, "updatedGroup", "updatedGroupDesc");
    }
}

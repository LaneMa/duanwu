package com.duanwu.dwb.controller;

import com.duanwu.dwb.model.Group;
import com.duanwu.dwb.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping
    public Object setGroup() {
        List<Group> value = groupService.setGroups();
        return  value;
    }
}

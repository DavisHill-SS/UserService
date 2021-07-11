package com.beardtrust.webapp.userrservice.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.beardtrust.webapp.userrservice.entities.UserEntity;
import com.beardtrust.webapp.userrservice.repos.UserRepository;
import com.beardtrust.webapp.userrservice.services.UserInfoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@RequestMapping("/beardtrust/users")
@RestController
@CrossOrigin(origins = "*")
public class UserInfoController {

    @Autowired
    private UserRepository repo;
    @Autowired
    private UserInfoService us = new UserInfoService(repo);

    @PostMapping("/update/{id}")
    public String updateUser(@RequestBody UserEntity u, @PathVariable String id) {
        return us.updateService(u, id);
    }

    @PreAuthorize("hasAuthority('admin') or principal == #id")
    @GetMapping("/view/all")//admin access
    public List<UserEntity> getAllUserInfos() {
        return us.getAllUserInfos();
    }

    @PostMapping("/specific/{account_id}")
    public Optional<UserEntity> getSpecificUserInfos(@PathVariable String account_id) {
        return us.getSpecificUserInfos(account_id);
    }
}

package com.jie.controller;


import com.jie.model.dto.UpdateRoleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserRole(@PathVariable(name = "userId") int userId, @RequestBody UpdateRoleDTO updateRoleDTO) {

    }

}

package org.example.authproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {


    @GetMapping("/public")
    public String testEndpoint() {
        return "Test endpoint is working!";
    }

    @GetMapping("/logged-in")
    public String testPrivateEndpoint() {
        return "Private endpoint is working!";
    }

    @GetMapping("/admin")
    public String testAdminEndpoint() {
        return "Admin endpoint is working!";
    }
    @GetMapping("/haspermission-read")
    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    public String testHasPermissionReadEndpoint() {
        return "Has permission read endpoint is working!";
    }

    @PreAuthorize("hasAuthority('PERMISSION_WRITE')")
    @GetMapping("/haspermission-write")
    public String testHasPermissionWriteEndpoint() {
        return "Has permission write endpoint is working!";
    }


}

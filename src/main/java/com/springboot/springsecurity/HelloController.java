package com.springboot.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

 

    // Public endpoint
    @GetMapping("/public")
    public String publicEndpoint() {
        return "This endpoint is public and accessible by anyone!";
    }

    // Private endpoint (requires authentication)
    @GetMapping("/hello")
    public String privateEndpoint() {
        return "Hello secure World!";
    }

}

package springbootsecurity.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class TestAuthController {

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public String helloGet() {
        return "Hello World - GET";
    }

    @PostMapping("/post")
    @PreAuthorize("hasAuthority('CREATE') or hasAuthority('CREATE')")
    public String helloPost() {
        return "Hello World - POST";
    }

    @PutMapping("/put")
    public String helloPut() {
        return "Hello World - PUT";
    }

    @DeleteMapping("/delete")
    public String helloDelete() {
        return "Hello World - DELETE";
    }

    @PatchMapping("/patch")
    @PreAuthorize("hasAuthority('REFACTOR')")
    public String helloPatch() {
        return "Hello World - Patch";
    }

    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/hello-secured")
    @PreAuthorize("hasAuthority('READ')")
    public String helloSecured() {
        return "Hello world secured";
    }

    @GetMapping("/hello-secured2")
    @PreAuthorize("hasAuthority('CREATE')")
    public String helloSecured2() {
        return "Hello world secured2";
    }
}

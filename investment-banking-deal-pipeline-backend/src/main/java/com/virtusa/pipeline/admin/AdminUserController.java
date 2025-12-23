package com.virtusa.pipeline.admin;

import com.virtusa.pipeline.auth.JwtUtil;
import com.virtusa.pipeline.user.User;
import com.virtusa.pipeline.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminUserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserController(UserRepository userRepository,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    public User createUser(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody CreateUserRequest request) {

        String token = authHeader.substring(7);
        String role = JwtUtil.extractRole(token);

        if (!"ADMIN".equals(role)) {
            throw new RuntimeException("Only ADMIN can create users");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setActive(true);

        return userRepository.save(user);
    }
}

package com.virtusa.pipeline.auth;

import com.virtusa.pipeline.user.User;
import com.virtusa.pipeline.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        // 1️⃣ Find user by username
        Optional<User> userOpt =
                userRepository.findByUsername(request.getUsername());

        if (userOpt.isEmpty()) {
            return "User not found";
        }

        User user = userOpt.get(); // ✅ NOW user EXISTS

        // 2️⃣ Check if user is active
        if (!user.isActive()) {
            return "User is inactive";
        }

        // 3️⃣ Check password
        if (!passwordEncoder.matches(
                request.getPassword(), user.getPassword())) {
            return "Invalid password";
        }

        // 4️⃣ Generate JWT token
        String token = JwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
        );

        // 5️⃣ Return token
        return token;
    }
}

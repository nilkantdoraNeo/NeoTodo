package com.neosoft.neosoftToDo.Controller;


import com.neosoft.neosoftToDo.Entity.AuthRequest;
import com.neosoft.neosoftToDo.Entity.AuthResponse;
import com.neosoft.neosoftToDo.Entity.Userss;
import com.neosoft.neosoftToDo.JwtAuthentication.JwtUtil;
import com.neosoft.neosoftToDo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            Optional<Userss> userOptional = userRepository.findByUsername(req.getUsername());

            if (userOptional.isPresent()) {
                Userss user = userOptional.get();

                if (passwordEncoder.matches(req.getPassword(), user.getPassword())) {
                    String token = jwtUtil.generateToken(user.getUsername());
                    return ResponseEntity.ok(new AuthResponse(token));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password  no match");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no User");
            }
        } catch (Exception e) {
            e.printStackTrace(); // log the exception in console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

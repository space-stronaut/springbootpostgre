package com.authenticate.belajar.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.authenticate.belajar.jwt.JwtGenerate;
import com.authenticate.belajar.models.Token;
// import com.authenticate.belajar.jwt.TokenResponse;
import com.authenticate.belajar.models.User;
import com.authenticate.belajar.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@RestController
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtGenerate jwtGenerate;

    @PostMapping("/user/register")
    public User register(@RequestBody User user){
        
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString();
        // String id = user.setId(String uid);
        String username = user.getUsername();
        String email = user.getEmail();
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        // String isActive = user.getIsActive();
        User userRequest = new User();
        userRequest.setId(uid);
        userRequest.setUsername(username);
        userRequest.setEmail(email);
        userRequest.setIsActive("Y");
        userRequest.setPassword(password);

        return userRepository.save(userRequest);
    }

    @PostMapping("/user/delete")
    public ResponseEntity<?> delete(@RequestBody User user){
        try {
            userRepository.deleteByUUID(user.getId());

            return ResponseEntity.ok("User already deleted");
        } catch (Exception e) {
            return ResponseEntity.status(200).body("User already deleted");
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String email = user.getEmail();
        User user2 = userRepository.getUserbyEmail(email);

        if (user2 != null) {
            String passwordResponse = user2.getPassword();
            String password = user.getPassword();

            if (BCrypt.checkpw(password, passwordResponse)) {
                String token = jwtGenerate.generateToken(user2.getId());
                Token tokenModel = new Token();
                tokenModel.setToken(token);

                // Include the token in the response
                return ResponseEntity.ok(tokenModel);
            }
        }

        // If the user is not found or authentication fails, return a 401 Unauthorized response.
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
    }

    @PostMapping("/user/authenticate")
    public ResponseEntity<?> authenticate(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
        
            String claims = jwtGenerate.parserToken(token);

            String userId = claims;

            User response = userRepository.getUserById(userId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Penanganan jika terjadi kesalahan dalam mendekripsi atau menemukan pengguna
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

}

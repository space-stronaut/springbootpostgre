package com.authenticate.belajar.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.authenticate.belajar.models.User;
import com.authenticate.belajar.repository.UserRepository;

@Service
@RestController
public class UserService {
    @Autowired
    UserRepository userRepository;

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
}

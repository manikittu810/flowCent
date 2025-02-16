package com.flowcent.authservice.controller;

import com.flowcent.authservice.dto.AuthRequest;
import com.flowcent.authservice.entity.User;
import com.flowcent.authservice.enumRole.Role;
import com.flowcent.authservice.repository.UserRepository;
import com.flowcent.authservice.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/auth")
public class AuthController {
    private  final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    public AuthController(UserRepository userRepository, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.jwtUtil=jwtUtil;
        this.passwordEncoder=new BCryptPasswordEncoder();
    }

    @PostMapping(path="/signup")
    public ResponseEntity<?> signUp(@RequestBody AuthRequest request){

        if(userRepository.findUserByUsername(request.getName()).isPresent()){
            return ResponseEntity.badRequest().body("Username already exists..");
        }

        if(userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()){
            return ResponseEntity.badRequest().body("Phone Number already exists");
        }

        User user = new User();
        user.setUsername(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRoles(Set.of(Role.USER));
        userRepository.save(user);
        return ResponseEntity.ok("User Successfully Registered");
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest request){
        Optional<User> user = userRepository.findUserByUsername(request.getName());
        if(user.isPresent() //checking if the user is existing in the database
                &&
                passwordEncoder.matches(request.getPassword(),
                user.get().getPassword()) //this is like, you are basically
            // checking the password which you want to signin with ,
            // which is in the form of a POST request
            // and comparing with the password which is already stored with that respective user with same password.
                //if these two matches then do the following :
        ){
            String token = jwtUtil.generateToken(user.get().getUsername(),
                    user.get()
                            .getRoles()
                            .stream()
                            .map(Enum::name)
                            .collect(Collectors.toSet())
                    );
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}

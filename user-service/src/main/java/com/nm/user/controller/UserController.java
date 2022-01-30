package com.nm.user.controller;

import com.nm.commonpojo.entities.User;
import com.nm.user.config.CustomUserDetailService;
import com.nm.user.models.AuthenticationRequest;
import com.nm.user.models.AuthenticationResponse;
import com.nm.user.service.UserService;
import com.nm.user.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
	private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;


	@Autowired
	private JwtUtil jwtTokenUtil;

    @GetMapping("/hello")
    public String getHello() {
        return "Hello world";
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User creatUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userService.save(user);
    }

    @GetMapping("/user-in-context")
    public ResponseEntity<User> getUserinContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            return new ResponseEntity<User>(userService.findByUserName(user.getUsername()),HttpStatus.OK);
        }else {
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println(passwordEncoder.encode(authenticationRequest.getPassword()));
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
            e.printStackTrace();
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = customUserDetailService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.AuthenticationRequest;
import com.example.demo.models.AuthenticationResponse;
import com.example.demo.models.UserModel;
import com.example.demo.models.UserRepository;

@RestController
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/subs")
		private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest){
		
		String username = authenticationRequest.getUsername();
		String password = authenticationRequest.getPassword();
		
		UserModel userModel = new UserModel();
		userModel.setUsername(username);
		userModel.setPassowrd(password);
		
		
		try {
			userRepository.save(userModel);
		} catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error during Subscroption of "+ username));
		}
		
		
		return ResponseEntity.ok(new AuthenticationResponse("Succesfull Subscroption of "+ username));
		
		 
		
		
	}
	
	@PostMapping("/auth")
	
	private ResponseEntity<?> subsribeClient(@RequestBody AuthenticationRequest authenticationRequest){
	
		String username = authenticationRequest.getUsername();
		String password = authenticationRequest.getPassword();
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		} catch(BadCredentialsException e) {
			
			return ResponseEntity.ok(new AuthenticationResponse("UnSuccesfull Authentication of "+ username));
		}
		
		
		
		
		return ResponseEntity.ok(new AuthenticationResponse("Succesfull Authentication of "+ username));
		
		
		
	}
	

}

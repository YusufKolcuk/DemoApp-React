package com.xperta.controller;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xperta.entity.User;
import com.xperta.error.ApiError;
import com.xperta.security.EncryptionHelper;
import com.xperta.service.UserService;

@RestController
public class AuthController {
	
	@Autowired
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/login/user")
	public ResponseEntity<?> handleAuthentication(@RequestHeader(name="Authorization",
	required = false) String authorization){

		if(authorization==null) {
			ApiError error=new ApiError(401, "unauthorized request", "api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		String base64encoded=authorization.split("Basic ")[1];
		String decoded=new String(Base64.getDecoder().decode(base64encoded));
		String[] parts = decoded.split(":");
		String usernamePart=parts[0];
		String passwordPart=parts[1];
		User user;
		user=userService.getFindByUsername(usernamePart);
		
		if(user==null) {
			ApiError error=new ApiError(401, "unauthorized request", "api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
			
		}

		String encryptedPW = EncryptionHelper.encrypt(passwordPart);
		String _passwordHash=EncryptionHelper.encrypt(encryptedPW);
		String password = user.getPassword();
		if(!password.equals(_passwordHash)) {
			logger.info("User Authentication with username[" + usernamePart + "]: NOK!");
			ApiError error=new ApiError(401, "unauthorized request", "api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		logger.info("User Authentication with username[" + usernamePart + "]: OK");
		user.setIsAdmin( userService.isAdmin(user) );
		

		return ResponseEntity.ok(user);
	}
}

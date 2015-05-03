package com.quakehelp.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.quakehelp.api.domain.User;

public class SpringUtils {

	public static String getUserName() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth == null) {
			throw new IllegalArgumentException("Authentication Required.");
		}
		Object principal = auth.getPrincipal();
		User user = (User) principal;
		return user.getUsername();
	}

}

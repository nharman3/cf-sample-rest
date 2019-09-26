package com.example.demo.rest;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

	@GetMapping("/hello")
	public String hello(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("User: ").append(userDetails == null ? "UNKNOWN" : userDetails.getUsername()).append(", ");
		
		Enumeration<String> headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String header = headers.nextElement();
			sb.append(header).append(": ").append(request.getHeader(header));
			if (headers.hasMoreElements()) {
				sb.append(", ");
			}
		}
		
		return sb.toString();
	}
	
}

package com.konkanvalley.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@GetMapping("/")
	public String getSecuredData() {
		return "Hello fucking world";
	}
}

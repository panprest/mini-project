package com.app.latihan.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.latihan.request.BookingRequest;
import com.app.latihan.request.BookingUpdate;
import com.app.latihan.response.BookingResponse;

@RestController
public class AdminController {

	@PostMapping
	public ResponseEntity<BookingResponse> newBook (BookingRequest request) {
		return null;
	}
	
	@GetMapping
	public ResponseEntity<BookingResponse> getAll () {
		return null;
	}
	
	@PutMapping
	public ResponseEntity<BookingResponse> updateBook (BookingUpdate update) {
		return null;
	}
	
	@DeleteMapping 
	public ResponseEntity<BookingResponse> deleteBook (String id) {
		return null;
	}
	
	@GetMapping 
	public ResponseEntity<BookingResponse> getById (@PathVariable String id) {
		return null;
	}
}

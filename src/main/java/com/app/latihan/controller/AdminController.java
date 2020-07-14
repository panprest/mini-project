package com.app.latihan.controller;

import java.text.ParseException;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.app.latihan.exception.CustomException;
import com.app.latihan.request.BookingRequest;
import com.app.latihan.request.BookingUpdate;
import com.app.latihan.response.BookingResponse;
import com.app.latihan.service.BookingService;
import com.app.latihan.util.UrlUtil;

@RestController
@EnableWebMvc
public class AdminController {

	@Autowired
	private BookingService bookingservice;
	
//	@Autowired
//	private TestService test;
	
	@PostMapping(value = UrlUtil.Uri.NEW)
	public ResponseEntity<BookingResponse> newBook (@RequestBody BookingRequest request) throws MessagingException, ParseException, CustomException {
		return new ResponseEntity<BookingResponse>(bookingservice.newBook(request), HttpStatus.CREATED);
	}
	
	@GetMapping("/booking/getAll")
	public ResponseEntity<BookingResponse> getAllData () {
		return new ResponseEntity<BookingResponse>(bookingservice.getAll(), HttpStatus.OK);
	}
	
	@PutMapping("/booking/update")
	public ResponseEntity<BookingResponse> updateBook (@RequestBody BookingUpdate update) throws CustomException, MessagingException {
		return new ResponseEntity<BookingResponse>(bookingservice.update(update), HttpStatus.OK);
	}
	
//	@DeleteMapping("/delete/{id}) 
//	public ResponseEntity<BookingResponse> deleteBook (@PathVariable String id) {
//		return null;
//	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<BookingResponse> getById (@PathVariable String id) {
		return new ResponseEntity<BookingResponse>(bookingservice.getId(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<BookingResponse> deleteSemua() {
		return new ResponseEntity<BookingResponse>(bookingservice.deleteSemua(), HttpStatus.OK);
	}
	
//	@PostMapping("/test")
//	public ResponseEntity<String> test(@RequestBody Map<String, String> message) {
//		test.test(message.get("message").toString());
//		return new ResponseEntity<String>("sukses", HttpStatus.OK);
//	}
}

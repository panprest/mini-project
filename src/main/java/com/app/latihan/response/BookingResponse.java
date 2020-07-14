package com.app.latihan.response;

import java.util.List;

import com.app.latihan.message.BookingMessage;
import com.app.latihan.mongo.model.BookingModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse extends BaseResponse {
	private BookingModel bookingMessage;
	private List<BookingModel> bookingMessageList;
	
	public BookingResponse(String message, String code, BookingModel bookingMessage) {
		super(message, code);
		this.bookingMessage = bookingMessage;
	}

	public BookingResponse(String message, String code, List<BookingModel> bookingMessageList) {
		super(message, code);
		this.bookingMessageList = bookingMessageList;
	}

	
	
}

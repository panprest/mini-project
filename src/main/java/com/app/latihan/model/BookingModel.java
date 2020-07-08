package com.app.latihan.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "booking")
public class BookingModel {
	@Id
	private String id;
	private String name; 
	private String asal;
	private String tujuan;
	

}

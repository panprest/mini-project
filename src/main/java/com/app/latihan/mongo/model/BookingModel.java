package com.app.latihan.mongo.model;

import java.util.Date;

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
	private String nama;
	private String asal;
	private String tujuan;
	private Integer harga;
	private String status;
	private String email;
	private Date tanggalBerangkat;
	

}

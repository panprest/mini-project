package com.app.latihan.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mongodb.annotations.NotThreadSafe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
		private String nama;
		private String tujuan;
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
		private Date tanggalBerangkat;
		private String email;
}

package com.app.latihan.message;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nama;
	private String asal;
	private String tujuan;
	private Integer harga;
	private String status;
	private Date tanggalBerangkat;
}

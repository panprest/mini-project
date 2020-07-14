package com.app.latihan.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.latihan.exception.CustomException;
import com.app.latihan.message.BookingMessage;
import com.app.latihan.mongo.model.BookingModel;
import com.app.latihan.repository.MongoRepo;
import com.app.latihan.request.BookingRequest;
import com.app.latihan.request.BookingUpdate;
import com.app.latihan.response.BookingResponse;
import com.app.latihan.util.NumberFormatUtil;
import com.app.latihan.util.ResponseUtil;

@Service
public class BookingService {
		@Autowired
		private MongoRepo mongoRepo;
		
		@Autowired
		private JavaMailSender emailSender;
	
		@Transactional	
		public BookingResponse newBook(BookingRequest bookingRequest) throws MessagingException, ParseException, CustomException {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date today = df.parse(df.format(new Date()));
			if (today.after(bookingRequest.getTanggalBerangkat())) {
				throw new CustomException("Tanggal tidak sesuai!");
			}
			
			BookingModel newBooked = BookingModel.builder().email(bookingRequest.getEmail())
					.asal("Bandung").tanggalBerangkat(bookingRequest.getTanggalBerangkat()).nama(bookingRequest.getNama()).tujuan(bookingRequest.getTujuan()).status("LUNAS").harga(50000).build();
			BookingModel saveBook = mongoRepo.save(newBooked);
			SimpleMailMessage message = new SimpleMailMessage();
			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			String htmlMsg = "<table style=\"height: 28px; background-color: #deb887; width: 705px;\">"+
					"<tbody>"+
					"<tr>"+
					"<td style=\"width: 246px; text-align: center;\">"+
					"<h3><span style=\"color: #ffffff;\"><img src=\"https://ci6.googleusercontent.com/proxy/ZmQs8sOGMawqlFkVsdx4DJAgS5U_bnBFTlt8piTEHYctwByEL1SVdjIKZEE9o7TfsxgWWESi2wybwT0y0l_l2UFd4xeC6nZWJUkJKA=s0-d-e1-ft#http://baraya.tiketux.com/templates/images/logo_small.png\" alt=\"\" width=\"100\" height=\"21\" /></span></h3>"+
					"</td>"+
					"<td style=\"width: 1144px; text-align: center;\">"+
					"<h3 style=\"text-align: center;\"><span style=\"color: #ffffff;\">BOOKING ONLINE</span></h3>"+
					"</td>"+
					"</tr>"+
					"</tbody>"+
					"</table>"+
					"<table style=\"height: 172px;\" width=\"704\">"+
					"<tbody>"+
					"<tr>"+
					"<td style=\"width: 694px; background-color: #ffe4c4;\" colspan=\"2\">Data Order :</td>"+
					"</tr>"+
					"<tr>"+
					"<td style=\"width: 194px;\">Nama :</td>"+
					"<td style=\"background-color: #7fff00;\"> "+newBooked.getNama()+" </td>"+
					"</tr>"+
					"<tr>"+
					"<td style=\"width: 194px;\">Tanggal Berangkat :</td>"+
					"<td style=\"background-color: #7fff00;\"> "+newBooked.getTanggalBerangkat()+"</td>"+
					"</tr>"+
					"<tr>"+
					"<td style=\"width: 194px;\">Tujuan :</td>"+
					"<td style=\"background-color: #7fff00;\"> "+newBooked.getTujuan()+"</td>"+
					"</tr>"+
					"<tr>"+
					"<td style=\"width: 194px;\">Harga :</td>"+
					"<td style=\"background-color: #7fff00;\">"+newBooked.getHarga()+" </td>"+
					"</tr>"+
					"<tr>"+
					"<td style=\"width: 194px;\">Status :</td>"+
					"<td style=\"background-color: #7fff00;\"> "+newBooked.getStatus()+"</td>"+
					"</tr>"+
					"<tr>"+
					"<td style=\"width: 194px;\">Kode :</td>"+
					"<td style=\"background-color: #7fff00;\"> "+newBooked.getId()+"</td>"+
					"</tr>"+
					"</tbody>"+
					"</table>"+
					"<p><strong> Terimakasih Atas Pesanan Anda!</strong></p>";
						

//			String htmlMsg = "<br>Pesanan Booking</br>" 
//					+ "<br></br>" 
//					+ "<br></br>" 
//					+ "<br> Data Order Anda :"
//					+ "<br></br>" 
//			+ "<br>Nama : "+newBooked.getNama()+" </br>"
//			+ "<br>Tanggal Berangkat : "+newBooked.getTanggalBerangkat()+" </br>"
//			+ "<br>Tujuan: "+newBooked.getTujuan()+" </br>"
//			+ "<br>Harga: "+newBooked.getHarga()+" </br>"
//			+ "<br>Status: "+newBooked.getStatus()+" </br>"
//					;
			helper.setText(htmlMsg, true);
			helper.setFrom(new InternetAddress("mail@companyxyz.com"));
			helper.setTo(bookingRequest.getEmail());
			helper.setSubject("Booking Order - "+newBooked.getId());
			emailSender.send(mimeMessage);
			
			
			message.setSubject("Booking Order - "+bookingRequest.getNama());
			message.setTo(bookingRequest.getEmail());
			message.setText("test");
			emailSender.send(message);
			
			return new BookingResponse(ResponseUtil.Message.CREATED, ResponseUtil.Code.CREATED, newBooked);
		}
		public BookingResponse getAll() {
			return new BookingResponse("Get All", ResponseUtil.Code.OK, new ArrayList(mongoRepo.findAll()));
		}
		
		public BookingResponse getId(String id) {
			Optional<BookingModel> booked = mongoRepo.findById(id);
			return new BookingResponse("OK", ResponseUtil.Code.OK, booked.get());
		}
		
		public BookingResponse update(BookingUpdate bookingUpdate) throws CustomException, MessagingException {
			Optional<BookingModel> booked = mongoRepo.findById(bookingUpdate.getId());
			if (booked.isEmpty()) {
				throw new CustomException("Data tidak ditemukan!");
			}
			BookingModel updateBooked = booked.get();
			updateBooked.setTujuan(bookingUpdate.getTujuan());
			updateBooked.setTanggalBerangkat(bookingUpdate.getTanggalBerangkat());
			updateBooked = mongoRepo.save(updateBooked);
			
			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			String htmlMsg = "<br>Update Pesanan Booking</br>" 
					+ "<br></br>" 
					+ "<br></br>" 
					+ "<br> Data Order Anda :"
					+ "<br></br>" 
			+ "<br>Nama : "+updateBooked.getNama()+" </br>"
			+ "<br>Tanggal Berangkat : "+updateBooked.getTanggalBerangkat()+" </br>"
			+ "<br>Tujuan: "+updateBooked.getTujuan()+" </br>"
			+ "<br>Harga: "+updateBooked.getHarga()+" </br>"
			+ "<br>Status: "+updateBooked.getStatus()+" </br>"
					;
			helper.setText(htmlMsg, true);
			helper.setFrom(new InternetAddress("mail@companyxyz.com"));
			helper.setTo(updateBooked.getEmail());
			helper.setSubject("Booking Order - "+updateBooked.getId());
			emailSender.send(mimeMessage);
			
			return new BookingResponse(ResponseUtil.Message.UPDATED, ResponseUtil.Code.OK, updateBooked);
		}
		
		public BookingResponse deleteSemua() {
			List<BookingModel> booked = mongoRepo.findAll();
			mongoRepo.deleteAll();
			return new BookingResponse(ResponseUtil.Message.DELETED, ResponseUtil.Code.OK, booked);
		}
}

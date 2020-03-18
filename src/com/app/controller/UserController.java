package com.app.controller;


import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Booking;
import com.app.pojos.ContactUs;
import com.app.pojos.Hotel;
import com.app.pojos.Otp;
import com.app.pojos.PayStatus;
import com.app.pojos.Payment;
import com.app.pojos.Role;
import com.app.pojos.Room;
import com.app.pojos.User;
import com.app.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(allowedHeaders="*")
public class UserController 
{
	@Autowired
	private IUserService service;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public UserController() 
	{
		System.out.println("in usercontroller");
	}
	
	@PostMapping("/register")
	public Integer register(@RequestBody User user)
	{
		
		System.out.println(user);
		String msg="successfully registered in stayo "+user.getEmail();
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("registration of stayo");
		mailMessage.setText(msg);
		user.setRole(Role.USER);
		if(service.registerUser(user)!=0)
		{	mailSender.send(mailMessage);
		return 1;
		}
		else return 0;
		
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user)
	{
		System.out.println(user);
		return service.login(user);
		
	}
	
	@PostMapping("/forgot")
	public boolean forgotPassword(@RequestBody User user)
	{
		System.out.println(user.getEmail());
		try {
			User user1= service.findByEmail(user.getEmail());
			if(user!=null)
			{
				Otp op=new Otp();
				op.setOtp(service.generateOtp());
				service.saveOtp(op);
				String msg="Your one time password for forgot password is = "+op.getOtp();
				SimpleMailMessage mailMessage=new SimpleMailMessage();
				mailMessage.setTo(user1.getEmail());
				mailMessage.setSubject("One Time Password");
				mailMessage.setText(msg);
				try {
					mailSender.send(mailMessage);
				} catch (MailException e) {
					System.out.println("inside mail exception");
					e.printStackTrace();
				}
				return true;
			}
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@PostMapping("/confirmOtp")
	public boolean confirmOtp(@RequestBody Otp otp)
	{
		Otp o=service.getOtp();
		System.out.println(otp.getOtp());
		System.out.println(o.getOtp());
		if(otp.getOtp()==o.getOtp())
		{
			service.deleteOtp();
			return true;
		}
		else
		{
			System.out.println("in false");
			return false;
		}
	}
	
	@PostMapping("/resetpassword")
	public boolean resetPassword(@RequestBody User user)
	{
		//System.out.println(user.getPassword());
		//System.out.println(user.getEmail());
		service.resetPassword(user);
		return true;
	}
	
	@PostMapping(value="/searchHotel",consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> searchHotel(@RequestBody Hotel hotel)
	{
		System.out.println(hotel);
		return service.searchHotel(hotel);
	}
	
	@PostMapping(value="/getRoomList")
	public List<Room> getRoomList(@RequestBody Hotel hotel)
	{
		System.out.println(hotel);
		return service.getSpecificRooms(hotel);
	}
	
	@PostMapping("/book/{user}/{room}")
	public Double bookRoom(@RequestBody Booking booking,@PathVariable int user,@PathVariable int room)
	{
		
		System.out.println(booking);
		System.out.println(user);
		System.out.println(room);
		Date date=new Date();
		System.out.println(date);
		System.out.println(booking.getDateFrom());
		System.out.println(booking.getDateFrom().compareTo(date));
		if(booking.getDateFrom().compareTo(booking.getDateTo())>0 || (booking.getDateFrom().getDate()-(date.getDate()))<0)
		{
			System.out.println("in compare");
			return null;
		}
		else
		{
			booking.setUser(service.getUserById(user));		
			//dao.getRoomById(room).setPrice(dao.getRoomById(room).getPrice()*booking.getDateFrom().compareTo(booking.getDateTo()));
			booking.setRoom(service.getRoomById(room));
			int a=service.bookRoom(booking);
			if(a!=0)
			{
				service.changeRoomStatusToBooked(room);
				
			}
			System.out.println(booking.getDateFrom().compareTo(booking.getDateTo())+"hiiii");
			return (service.getRoomById(room).getPrice())*((booking.getDateTo().getDate())-booking.getDateFrom().getDate());
		}		
	}
	
	@GetMapping
	public Integer changeStatusToAvailable()
	{
		Date date=new Date();
		List<Booking> list=service.getBooking(date);
		for (Booking bk : list) {
			service.changeRoomStatusToAvailable(bk.getRoom().getId());
			service.deleteBooking(bk.getId());
			service.deletePayment(bk.getRoom());	
		}
		
		return 1;
	}
	
	@PostMapping("/cancelbooking")
	public Integer cancelBooking(@RequestBody int id)
	{
		Booking bk=service.getBookingById(id);
		service.changeRoomStatusToAvailable(bk.getRoom().getId());
		service.deleteBooking(bk.getId());
		//dao.deletePayment(bk.getRoom());
		return 1;
	}
	
	@PostMapping("/contactUs")
	public Integer contactUs(@RequestBody ContactUs contactus)
	{
		if(service.contact(contactus)!=null)
		{
			String msg="Thank you for giving your valuable feedback in stayo "+contactus.getEmail();
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setTo(contactus.getEmail());
			mailMessage.setSubject("contact to stayo");
			mailMessage.setText(msg);
			mailSender.send(mailMessage);
		}
		return service.contact(contactus);
	}
	
	@PostMapping(value="/getBooking",consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Booking> getBookings(@RequestBody User user)
	{
		//User user=dao.getUserById(id);
		return service.getBookings(user);
	}
	
	@PostMapping("/pay/{id}")
	public Integer makePayment(@PathVariable int id,@RequestBody Payment payment)
	{
		Room room=service.getRoomById(id);
		Date date=new Date();
		payment.setDate(date);
		payment.setRoom(room);
		payment.setPaystatus(PayStatus.PAYED);
		String msg="payment successful in stayo ";
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setTo(room.getBooking().getUser().getEmail());
		mailMessage.setSubject("Booking of stayo");
		mailMessage.setText(msg);
		mailSender.send(mailMessage);
		return service.makePayment(payment);
	}
}

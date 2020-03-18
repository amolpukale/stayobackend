package com.app.service;

import java.util.Date;
import java.util.List;

import com.app.pojos.Booking;
import com.app.pojos.ContactUs;
import com.app.pojos.Hotel;
import com.app.pojos.Otp;
import com.app.pojos.Payment;
import com.app.pojos.Room;
import com.app.pojos.User;

public interface IUserService 
{
	public Integer registerUser(User user);
	public User login(User user);
	public User findByEmail(String email);
	public int generateOtp();
	public void saveOtp(Otp otp);
	public Otp getOtp();
	public void deleteOtp();
	public void resetPassword(User user);
	public List<Hotel> searchHotel(Hotel hotel);
	public List<Room> getSpecificRooms(Hotel hotel);
	public Integer bookRoom(Booking booking);
	public User getUserById(int userid);
	public Room getRoomById(int roomid);
	public void changeRoomStatusToBooked(int roomid);
	public Integer contact(ContactUs contactus);
	public List<Booking> getBookings(User id);
	public List<Booking> getBooking(Date dateTo);
	public void changeRoomStatusToAvailable(int id);
	public void deleteBooking(int id);
	public Integer makePayment(Payment payment);
	public void deletePayment(Room id);
	public Booking getBookingById(int id);
}

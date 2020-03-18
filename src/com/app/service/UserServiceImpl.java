package com.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IUserDao;
import com.app.pojos.Booking;
import com.app.pojos.ContactUs;
import com.app.pojos.Hotel;
import com.app.pojos.Otp;
import com.app.pojos.Payment;
import com.app.pojos.Room;
import com.app.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao dao;
	
	@Override
	public Integer registerUser(User user) {
		// TODO Auto-generated method stub
		return dao.registerUser(user);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return dao.login(user);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}

	@Override
	public int generateOtp() {
		// TODO Auto-generated method stub
		return dao.generateOtp();
	}

	@Override
	public void saveOtp(Otp otp) {
		// TODO Auto-generated method stub
		 dao.saveOtp(otp);
	}

	@Override
	public Otp getOtp() {
		// TODO Auto-generated method stub
		return dao.getOtp();
	}

	@Override
	public void deleteOtp() {
		// TODO Auto-generated method stub
		dao.deleteOtp();
	}

	@Override
	public void resetPassword(User user) {
		// TODO Auto-generated method stub
		dao.resetPassword(user);
	}

	@Override
	public List<Hotel> searchHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		return dao.searchHotel(hotel);
	}

	@Override
	public List<Room> getSpecificRooms(Hotel hotel) {
		// TODO Auto-generated method stub
		return dao.getSpecificRooms(hotel);
	}

	@Override
	public Integer bookRoom(Booking booking) {
		// TODO Auto-generated method stub
		return dao.bookRoom(booking);
	}

	@Override
	public User getUserById(int userid) {
		// TODO Auto-generated method stub
		return dao.getUserById(userid);
	}

	@Override
	public Room getRoomById(int roomid) {
		// TODO Auto-generated method stub
		return dao.getRoomById(roomid);
	}

	@Override
	public void changeRoomStatusToBooked(int roomid) {
		// TODO Auto-generated method stub
		dao.changeRoomStatusToBooked(roomid);
	}

	@Override
	public Integer contact(ContactUs contactus) {
		// TODO Auto-generated method stub
		return dao.contact(contactus);
	}

	@Override
	public List<Booking> getBookings(User id) {
		// TODO Auto-generated method stub
		return dao.getBookings(id);
	}

	@Override
	public List<Booking> getBooking(Date dateTo) {
		// TODO Auto-generated method stub
		return dao.getBooking(dateTo);
	}

	@Override
	public void changeRoomStatusToAvailable(int id) {
		// TODO Auto-generated method stub
		dao.changeRoomStatusToAvailable(id);
	}

	@Override
	public void deleteBooking(int id) {
		// TODO Auto-generated method stub
		dao.deleteBooking(id);
	}

	@Override
	public Integer makePayment(Payment payment) {
		// TODO Auto-generated method stub
		return dao.makePayment(payment);
	}

	@Override
	public void deletePayment(Room id) {
		// TODO Auto-generated method stub
		dao.deletePayment(id);
	}

	@Override
	public Booking getBookingById(int id) {
		// TODO Auto-generated method stub
		return dao.getBookingById(id);
	}

}

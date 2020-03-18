package com.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IAdminDao;
import com.app.pojos.Booking;
import com.app.pojos.Hotel;
import com.app.pojos.Room;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService 
{
	@Autowired 
	private IAdminDao dao;

	@Override
	public Integer addHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		return dao.addHotel(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		// TODO Auto-generated method stub
		return dao.getAllHotels();
	}

	@Override
	public Integer addRoom(Room room) {
		// TODO Auto-generated method stub
		return dao.addRoom(room);
	}

	@Override
	public Hotel getHotelById(int id) {
		// TODO Auto-generated method stub
		return dao.getHotelById(id);
	}

	@Override
	public boolean deleteHotel(int id) {
		// TODO Auto-generated method stub
		return dao.deleteHotel(id);
	}

	@Override
	public Hotel getHotelDetails(int id) {
		// TODO Auto-generated method stub
		return dao.getHotelDetails(id);
	}

	@Override
	public List<Room> getRooms(Hotel hotelId) {
		// TODO Auto-generated method stub
		return dao.getRooms(hotelId);
	}

	@Override
	public List<Booking> getBookings(Date dateTo) {
		// TODO Auto-generated method stub
		return dao.getBookings(dateTo);
	}

	@Override
	public Integer updateHotelDetails(int hotelId, Hotel hotel) {
		// TODO Auto-generated method stub
		return dao.updateHotelDetails(hotelId, hotel);
	}

	@Override
	public boolean deleteRoom(int id) {
		// TODO Auto-generated method stub
		return dao.deleteRoom(id);
	}

	@Override
	public Integer updateRoomDetails(Room room) {
		// TODO Auto-generated method stub
		return dao.updateRoomDetails(room);
	}

	@Override
	public List<Booking> showAllBooking() {
		// TODO Auto-generated method stub
		return dao.showAllBooking();
	}
	
	
}

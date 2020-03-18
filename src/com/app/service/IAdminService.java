package com.app.service;

import java.util.Date;
import java.util.List;

import com.app.pojos.Booking;
import com.app.pojos.Hotel;
import com.app.pojos.Room;

public interface IAdminService 
{
	public Integer addHotel(Hotel hotel); 
	public List<Hotel> getAllHotels();
	public Integer addRoom(Room room);
	public Hotel getHotelById(int id);
	public boolean deleteHotel(int id); 
	public Hotel getHotelDetails(int id);
	public List<Room> getRooms(Hotel hotelId);
	public List<Booking> getBookings(Date dateTo);
	public Integer updateHotelDetails(int hotelId,Hotel hotel);
	public boolean deleteRoom(int id);
	public Integer updateRoomDetails(Room room);
	public List<Booking> showAllBooking();
}

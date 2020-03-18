package com.app.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Booking;
import com.app.pojos.Hotel;
import com.app.pojos.Room;


@Repository
public class AdminDaoImpl implements IAdminDao 
{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public Integer addHotel(Hotel hotel) 
	{
		return (Integer) sf.getCurrentSession().save(hotel);		
	}
	
	@Override
	public List<Hotel> getAllHotels() {
		return sf.getCurrentSession().createQuery("select distinct h from Hotel h left outer join fetch h.roomlist",Hotel.class).getResultList();
	}
	
	@Override
	public Hotel getHotelDetails(int id)
	{
		System.out.println(id);
		System.out.println("in get hoteldetail method");
		return sf.getCurrentSession().createQuery("select h from Hotel h left outer join fetch h.roomlist where h.hotelId=:hm", Hotel.class).setParameter("hm", id).getSingleResult();
	}
	
	@Override
	public Integer addRoom(Room room) {
		System.out.println("in add room method");
		return (Integer) sf.getCurrentSession().save(room);
	}

	@Override
	public Hotel getHotelById(int id) {
		return sf.getCurrentSession().get(Hotel.class, id);
	//return sf.getCurrentSession().createQuery("select h from Hotel h  inner join fetch h.roomlist where h.hotelId=:hm", Hotel.class)
	}
	
	@Override
	public boolean deleteHotel(int id) {
	Hotel hotel=sf.getCurrentSession().get(Hotel.class,id);
	sf.getCurrentSession().delete(hotel);
	return true;
	}
	
	@Override
	public Integer updateHotelDetails(int hotelId, Hotel hotel) {
	//sf.getCurrentSession().get(Hotel.class,hotelId);
		hotel.setHotelId(hotelId);
	sf.getCurrentSession().update(hotel);
		return 1;
	}
	
	@Override
	public List<Room> getRooms(Hotel hotelId) {
		return sf.getCurrentSession().createQuery("select r from Room r where r.hotelId=:hm order by roomNo",Room.class).setParameter("hm",hotelId).getResultList();
	}
	
	public List<Booking> getBookings(Date dateTo) {
		return sf.getCurrentSession().createQuery("select b from Booking b where b.dateTo=:dt",Booking.class).setParameter("dt",dateTo).getResultList();
	}

	@Override
	public boolean deleteRoom(int id) {
	Room room=sf.getCurrentSession().get(Room.class,id);
	sf.getCurrentSession().delete(room);
		return true;
	}
	
	@Override
	public Integer updateRoomDetails(Room room) {
		System.out.println(room);
	sf.getCurrentSession().update(room);
		return 1;
	}
	
	@Override
	public List<Booking> showAllBooking() {
		return sf.getCurrentSession().createQuery("select b from Booking b left outer join fetch b.user",Booking.class).getResultList();
	}
}

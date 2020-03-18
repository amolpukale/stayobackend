package com.app.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;


import com.app.pojos.Booking;
import com.app.pojos.ContactUs;
import com.app.pojos.Hotel;
import com.app.pojos.Otp;
import com.app.pojos.Payment;
import com.app.pojos.Room;
import com.app.pojos.Status;
import com.app.pojos.User;

@Repository
public class UserDaoImpl implements IUserDao 
{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public Integer registerUser(User user) {
	
		if(sf.getCurrentSession().createQuery("select u from User u where u.username=:un",User.class).setParameter("un",user.getUsername()).getSingleResult()==null)
		return (Integer) sf.getCurrentSession().save(user);
		else
			return 0;
	}
	
	@Override
	public User login(User user) 
	{
		String jpql = "select u from User u where u.username = :um and u.password=:pass";
		return sf.getCurrentSession().createQuery(jpql, User.class).setParameter("um", user.getUsername())
				.setParameter("pass", user.getPassword()).getSingleResult();
	}
	
	public User findByEmail(String email) {
		String str = "select u from User u where u.email=:em";
		return sf.getCurrentSession().createQuery(str,User.class).setParameter("em",email).
				getSingleResult();
	}

	public int generateOtp() 
	{
		Random random = new Random();
		int num = random.nextInt(99999) + 99999;
		if (num < 100000 || num > 999999) 
		{
			num = random.nextInt(99999) + 99999;
			if (num < 100000 || num > 999999)
			{
				System.out.println("Unable to generate PIN at this time..");
			}
		}
		return num;		
	}
	
	@Override
	public void saveOtp(Otp otp) {
		
	sf.getCurrentSession().save(otp);
	}
	
	@Override
	public Otp getOtp() {
		// TODO Auto-generated method stub
		return sf.getCurrentSession().createQuery("select o from Otp o",Otp.class).getSingleResult();
	}
	
	@Override
	public void deleteOtp() 
	{
		 Otp o=sf.getCurrentSession().createQuery("select o from Otp o",Otp.class).getSingleResult();
		 sf.getCurrentSession().delete(o);
	}
	
	@Override
	public void resetPassword(User user) 
	{
		String str = "select u from User u where u.email=:em";
		User u=sf.getCurrentSession().createQuery(str,User.class).setParameter("em",user.getEmail()).getSingleResult();
		u.setPassword(user.getPassword());
		sf.getCurrentSession().save(u);
	}
	
	@Override
	public List<Hotel> searchHotel(Hotel hotel) 
	{

		String str="select h from Hotel h where lower(h.city) like lower(concat('%',:ct,'%'))";
		return sf.getCurrentSession().createQuery(str,Hotel.class).setParameter("ct",hotel.getCity()).getResultList();
	}
	
	@Override
	public List<Room> getSpecificRooms(Hotel hotel) {
		return sf.getCurrentSession().createQuery("select r from Room r where r.hotelId=:hm and r.status='AVAILABLE' order by roomNo",Room.class).setParameter("hm",hotel).getResultList();
	}
	
	@Override
	public Integer bookRoom(Booking booking) {
		return (Integer) sf.getCurrentSession().save(booking);
	}
	
	@Override
	public User getUserById(int userid) {
		return sf.getCurrentSession().get(User.class,userid);
	}
	
	@Override
	public Room getRoomById(int roomid) {
		return sf.getCurrentSession().get(Room.class,roomid);
	}
	
	@Override
	public void changeRoomStatusToBooked(int roomid) {
		Room room=sf.getCurrentSession().get(Room.class,roomid);
		room.setStatus(Status.BOOKED);
		sf.getCurrentSession().save(room);
	}
	
	@Override
	public Integer contact(ContactUs contactus) {
		return (Integer) sf.getCurrentSession().save(contactus);
	}
	
	@Override
	public List<Booking> getBookings(User id) {
		return sf.getCurrentSession().createQuery("select b from Booking b where b.user=:us",Booking.class).setParameter("us",id).getResultList();
	}
	
	public List<Booking> getBooking(Date dateTo) {
		return sf.getCurrentSession().createQuery("select b from Booking b where b.dateTo=:dt",Booking.class).setParameter("dt",dateTo).getResultList();
	}
	
	public void changeRoomStatusToAvailable(int id)
	{
		Room room=sf.getCurrentSession().get(Room.class,id);
		room.setStatus(Status.AVAILABLE);
		sf.getCurrentSession().save(room);
	}
	
	 public void deleteBooking(int id)
	 {
		 Booking booking=sf.getCurrentSession().get(Booking.class, id);
		 sf.getCurrentSession().delete(booking);
	 }
	 
	 @Override
	public Integer makePayment(Payment payment) {
		return (Integer) sf.getCurrentSession().save(payment);
	}
	 
	 @Override
	public void deletePayment(Room id) {
	Payment payment=(Payment) sf.getCurrentSession().createQuery("select p from Payment where p.room=:rm").setParameter("rm",id).getSingleResult();
	sf.getCurrentSession().delete(payment);
	}
	 
	 @Override
	public Booking getBookingById(int id) {
		return sf.getCurrentSession().get(Booking.class,id);
	}
	
}

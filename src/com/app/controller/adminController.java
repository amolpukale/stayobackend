package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import com.app.pojos.Booking;
import com.app.pojos.Hotel;
import com.app.pojos.Room;
import com.app.pojos.RoomType;
import com.app.pojos.Status;
import com.app.service.IAdminService;


@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("/admin")
public class adminController 
{
	@Autowired
	private IAdminService service;
	
//	@Value("${upload_image_path}")
//	private String imagePath;
	
	public adminController() {
	System.out.println("in admin controller");
	}
	
//	@PostMapping("/addhotel")
//	public Integer addHotel(@RequestBody Hotel hotel)
//	{
//		System.out.println(hotel);
//		return dao.addHotel(hotel);
//	}
	
	
	@PostMapping("/addhotel")
	public Integer addHotel(@RequestParam String hotelName,@RequestParam String address,@RequestParam String city,
			@RequestParam String state,@RequestParam String pincode,@RequestParam String landline,
			@RequestParam String mail,@RequestParam String website,@RequestParam String features,
			@RequestParam(value="image",required=false) MultipartFile image)
	
	{
		Hotel hotel=new Hotel(hotelName,address,city,state,pincode,landline,mail,website,features);
		System.out.println(hotel);	
		
		if(image!=null)
		{
			System.out.println("in Image");
			
			try {
			
				hotel.setImage(image.getBytes());
			} catch (Exception e1) {
				System.out.println("in exception");
				e1.printStackTrace();
			}
		}
		return service.addHotel(hotel);
	}
	
	@PostMapping("/addroom/{hotelID}")
	public Integer addRoom(@PathVariable int hotelID,@RequestParam int roomNo,@RequestParam RoomType roomtype,@RequestParam Status status,
			@RequestParam Double price,@RequestParam int maxOccupancy,@RequestParam String description,
			@RequestParam(value="image",required=false) MultipartFile image)
	{
	Room room=new Room(roomNo, roomtype, description, maxOccupancy, status, price);
		System.out.println(hotelID);
		room.setHotelId(service.getHotelDetails(hotelID));
		if(image!=null)
		{
			System.out.println("in Image");
			
			try {
			
				room.setImage(image.getBytes());
			} catch (Exception e1) {
				System.out.println("in exception");
				e1.printStackTrace();
			}
		}
		
		System.out.println(room);
		return service.addRoom(room);
	}
	
	@GetMapping
	public List<Hotel> getAllHotels()
	{
		return service.getAllHotels();
	}
	
	@GetMapping("/delete/{hotelId}")
	public boolean deleteHotel(@PathVariable int hotelId)
	{
		System.out.println(hotelId);
		return service.deleteHotel(hotelId);
	}
	
	@GetMapping("/getHotelDetails/{hotelId}")
	public Hotel getHotelDetails(@PathVariable int hotelId)
	{
		System.out.println(hotelId);
		System.out.println(service.getHotelDetails(hotelId));
		return service.getHotelDetails(hotelId);
	}
	
	@PostMapping("/updateHotel/{hotelId}")
	public Integer updateHotel(@PathVariable int hotelId,@RequestParam String hotelName,@RequestParam String address,@RequestParam String city,
			@RequestParam String state,@RequestParam String pincode,@RequestParam String landline,
			@RequestParam String mail,@RequestParam String website,@RequestParam String features,
			@RequestParam(value="image",required=false) MultipartFile image)
	{
		Hotel hotel=new Hotel(hotelName,address,city,state,pincode,landline,mail,website,features);
		if(image!=null)
		{
			System.out.println("in Image");
			
			try {
			
				hotel.setImage(image.getBytes());
			} catch (Exception e1) {
				System.out.println("in exception");
				e1.printStackTrace();
			}
		}
		return service.updateHotelDetails(hotelId,hotel);
	}
	
	@PostMapping("/updateRoom/{hotelId}")
	public Integer updateRoom(@PathVariable int hotelId,@RequestParam int id,@RequestParam int roomNo,@RequestParam RoomType roomtype,@RequestParam Status status,
			@RequestParam Double price,@RequestParam int maxOccupancy,@RequestParam String description,
			@RequestParam(value="image",required=false) MultipartFile image)
	{
		Room room=new Room(id,roomNo, roomtype, description, maxOccupancy, status, price);
		room.setHotelId(service.getHotelDetails(hotelId));
		if(image!=null)
		{
			System.out.println("in Image");
			
			try {
			
				room.setImage(image.getBytes());
			} catch (Exception e1) {
				System.out.println("in exception");
				e1.printStackTrace();
			}
		}
		return service.updateRoomDetails(room);
	}
	
	@PostMapping("/getRooms")
	public List<Room> getRooms(@RequestBody Hotel hotelId)
	{
		return service.getRooms(hotelId);
	}
	
	@DeleteMapping("/deleteRoom/{id}")
	public boolean deleteRoom(@PathVariable int id)
	{
		System.out.println(id);
		return service.deleteRoom(id);
	}
	
	@GetMapping("/showallbooking")
	public List<Booking> showAllBookings()
	{
		return service.showAllBooking();
	}
	
}

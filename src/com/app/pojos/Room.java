package com.app.pojos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="rooms")
public class Room extends Base
{
	private Integer id;
	private Hotel hotelId;
	private Integer roomNo;
	private RoomType roomtype;
	private String description;
	private Integer maxOccupancy;
	private Status status;
	private Double price;
	private Booking booking;
	private List<Payment> payment;
	private byte[] image;
	
	public Room() 
	{
		System.out.println("in room ctor");
	}

	public Room(Hotel hotelId, int roomNo, RoomType roomtype, String description, int maxOccupancy, Status status,
			Double price) {
		super();
		this.hotelId = hotelId;
		this.roomNo = roomNo;
		this.roomtype = roomtype;
		this.description = description;
		this.maxOccupancy = maxOccupancy;
		this.status = status;
		this.price = price;
	}

	public Room(Integer id, Integer roomNo, RoomType roomtype, String description, Integer maxOccupancy, Status status,
			Double price) {
		super();
		this.id = id;
		this.roomNo = roomNo;
		this.roomtype = roomtype;
		this.description = description;
		this.maxOccupancy = maxOccupancy;
		this.status = status;
		this.price = price;
	}

	public Room(Integer roomNo, RoomType roomtype, String description, Integer maxOccupancy, Status status,
			Double price) {
		super();
		this.roomNo = roomNo;
		this.roomtype = roomtype;
		this.description = description;
		this.maxOccupancy = maxOccupancy;
		this.status = status;
		this.price = price;
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonIgnore
	//@JsonBackReference
	@ManyToOne
	@JoinColumn(name="h_id")
	public Hotel getHotelId() {
		return hotelId;
	}

	public void setHotelId(Hotel hotelId) {
		this.hotelId = hotelId;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	@Enumerated(EnumType.STRING)
	@Column(length=20)
	public RoomType getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(RoomType roomtype) {
		this.roomtype = roomtype;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	@Enumerated(EnumType.STRING)
	@Column(length=20)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@JsonIgnore
	@OneToOne(mappedBy="room",cascade=CascadeType.REMOVE,orphanRemoval=true)
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy="room",cascade=CascadeType.ALL,orphanRemoval=true)
	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}
	
	@Lob
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", hotelId=" + hotelId + ", roomNo=" + roomNo + ", roomtype=" + roomtype
				+ ", description=" + description + ", maxOccupancy=" + maxOccupancy + ", status=" + status + ", price="
				+ price + "]";
	}



	


	
	
}

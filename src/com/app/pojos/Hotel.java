package com.app.pojos;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="hotels")
public class Hotel extends Base
{
	private Integer hotelId;
	private String hotelName;
	private String address,city,state,pincode,landline,mail,website,features;
	private List<Room> roomlist;
	private byte[] image;
	
	public Hotel() {
	System.out.println("in Hotel pojo");
	}

	public Hotel(String hotelName, String address, String city, String state, String pincode, String landline,
			String mail, String website, String features) {
		super();
		this.hotelName = hotelName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.landline = landline;
		this.mail = mail;
		this.website = website;
		this.features = features;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(length=30)
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	@Column(length=30)
	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}
	
	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy="hotelId",cascade=CascadeType.ALL,orphanRemoval=true)
	public List<Room> getRoomlist() {
		return roomlist;
	}

	public void setRoomlist(List<Room> roomlist) {
		this.roomlist = roomlist;
	}
	
	//@JsonIgnore
	@Lob
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", pincode=" + pincode + ", landline=" + landline + ", mail=" + mail
				+ ", website=" + website + ", features=" + features + "]";
	}

	
	
}

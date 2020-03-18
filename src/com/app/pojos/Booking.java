package com.app.pojos;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="bookings")
public class Booking extends Base
{
	private Integer id;
	@JsonIgnore
	private Room room;
	@JsonIgnore
	private User user;
	private Date dateTo;
	private Date dateFrom;
	private int noOfChild;
	private int noOfAdults;
	
	public Booking() {
	System.out.println("in booking pojo");
	}

	public Booking(Room room, User user, Date dateTo, Date dateFrom, int noOfChild, int noOfAdults) {
		super();
		this.room = room;
		this.user = user;
		this.dateTo = dateTo;
		this.dateFrom = dateFrom;
		this.noOfChild = noOfChild;
		this.noOfAdults = noOfAdults;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name="r_id")
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@ManyToOne
	@JoinColumn(name="u_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.DATE)
	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	@Temporal(TemporalType.DATE)
	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	public int getNoOfChild() {
		return noOfChild;
	}

	public void setNoOfChild(int noOfChild) {
		this.noOfChild = noOfChild;
	}

	public int getNoOfAdults() {
		return noOfAdults;
	}

	public void setNoOfAdults(int noOfAdults) {
		this.noOfAdults = noOfAdults;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", room=" + room + ", user=" + user + ", dateTo=" + dateTo + ", dateFrom="
				+ dateFrom + ", noOfChild=" + noOfChild + ", noOfAdults=" + noOfAdults + "]";
	}

	
	
}

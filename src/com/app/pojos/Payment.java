package com.app.pojos;



import java.util.Date;

import javax.persistence.*;



@Entity
@Table(name="payment")
public class Payment extends Base
{
	private Integer id;
	private Room room;
	private Double payment;
	private Date date;
	private PayStatus paystatus;
	
	public Payment() {
	System.out.println("in payment pojo");
	}

	public Payment(Room room, Double payment, Date date, PayStatus paystatus) {
		super();
		this.room = room;
		this.payment = payment;
		this.date = date;
		this.paystatus = paystatus;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	@ManyToOne
	@JoinColumn(name="r_id")
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Enumerated(EnumType.STRING)
	public PayStatus getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(PayStatus paystatus) {
		this.paystatus = paystatus;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", room=" + room + ", payment=" + payment + ", date=" + date + ", paystatus="
				+ paystatus + "]";
	}
	
	
}

package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contactUs")
public class ContactUs extends Base
{
	private Integer contactId;
	private String name,email,phone,subject,message;
	
	public ContactUs() {
	System.out.println("in contact us");
	}

	public ContactUs(String name, String email, String phone, String subject, String message) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.subject = subject;
		this.message = message;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ContactUs [contactId=" + contactId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", subject=" + subject + ", message=" + message + "]";
	}
	
	
}

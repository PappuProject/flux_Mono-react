package com.verizon.learning.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CustomerAddress {

	@Id
	private String customerAddressId;

	private String customerId;
	private String line1;
	private String line2;
	private String city;
	private String type;

	public CustomerAddress() {
		// TODO Auto-generated constructor stub
	}

	public CustomerAddress(String addressId, String customerId, String line1, String line2, String city, String type) {

		this.customerAddressId = addressId;
		this.customerId = customerId;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.type = type;

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCustomerAddressId() {
		return customerAddressId;
	}

	public void setCustomerAddressId(String customerAddressId) {
		this.customerAddressId = customerAddressId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {

		return this.customerId + " -- " + this.line1 + " -- " + this.city;
	}
}

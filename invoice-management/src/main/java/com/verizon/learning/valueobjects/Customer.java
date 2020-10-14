package com.verizon.learning.valueobjects;

import java.util.List;

public class Customer {

	private String id;

	private String name;

	private List<CustomerAddress> addresses;

	public Customer() {

	}

	public Customer(String id, String name) {
		this.id = id;
		this.name = name;

	}
//
//    public Customer(String id, String name, List<CustomerAddress> addresses) {
//        this.id = id;
//        this.name = name;
//        this.addresses = addresses;
//
//    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CustomerAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<CustomerAddress> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {

		return this.id + " -- " + this.name;
	}

}

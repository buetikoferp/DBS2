package ch.hsr.dbs2.jpa_exercise.model;

import javax.persistence.*;

/**
 * The persistent class for the address database table.
 *
 */

@Entity
public class Address {
	
	@Id
	private long id;
	public String street;
	public int zip;
	public String city;
	public BankManager manager;
	public BankCustomer customer;
	
	public Address(){}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public BankManager getManager() {
		return manager;
	}

	public void setManager(BankManager manager) {
		this.manager = manager;
	}

	public BankCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(BankCustomer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "\t" + "TODO";
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}

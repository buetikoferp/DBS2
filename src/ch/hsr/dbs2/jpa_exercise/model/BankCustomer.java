package ch.hsr.dbs2.jpa_exercise.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * The persistent class for the bankcustomer database table.
 *
 */

@Entity
public class BankCustomer {
	
	@ManyToMany(mappedBy = "customers")
	private Collection<BankManager> managers = new ArrayList<>();
	
	@OneToMany
	@JoinColumn(name ="customerref", referencedColumnName = "customerid")
	private Collection<BankAccount> accounts = new ArrayList<>();
	
	@OneToOne(optional = true)
	@JoinColumn(name = "addressref")
	public Address address;
	
	@Id
	private long customerid;
	public String name;
	public String birthDate;
	public BankManager manager;

	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public BankCustomer(){}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public BankManager getManager() {
		return manager;
	}

	public void setManager(BankManager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "\t" + "TODO";
	}
	
	public long getId() {
		return customerid;
	}

	public void setId(long id) {
		this.customerid = id;
	}


}

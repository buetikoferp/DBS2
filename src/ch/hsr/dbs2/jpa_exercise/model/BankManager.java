package ch.hsr.dbs2.jpa_exercise.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * The persistent class for the bankmanager database table.
 *
 */

@Entity
public class BankManager {
	
	@Id
	private long managerid;
	public String name;
	
	@ManyToMany
	@JoinTable(name = "customer_manager",
	joinColumns = {@JoinColumn(name = "managerref")},
	inverseJoinColumns = {@JoinColumn(name = "customerref")})
	private Collection<BankCustomer> customers = new ArrayList<>();
	
	@OneToOne(optional = true)
	@JoinColumn(name = "addressref")
	public Address address;
	
	public BankManager(){}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "\t" + "TODO";
	}
	
	public long getId() {
		return managerid;
	}

	public void setId(long id) {
		this.managerid = id;
	}


}

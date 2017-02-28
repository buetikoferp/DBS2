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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long managerid;
	public long getManagerid() {
		return managerid;
	}

	public void setManagerid(long managerid) {
		this.managerid = managerid;
	}

	public Collection<BankCustomer> getCustomers() {
		return customers;
	}

	public void setCustomers(Collection<BankCustomer> customers) {
		this.customers = customers;
	}

	public String name;
	
	@ManyToMany
	@JoinTable(name = "CustomerManager",
	joinColumns = {@JoinColumn(name = "ManagerId")},
	inverseJoinColumns = {@JoinColumn(name = "CustomerId")})
	private Collection<BankCustomer> customers = new ArrayList<>();
	
	@OneToOne(optional = true)
	@JoinColumn(name = "Manager_AddressId")
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
		return this.getClass().getSimpleName() + "\t" + name + "\t";
	}
	
	public long getId() {
		return managerid;
	}

	public void setId(long id) {
		this.managerid = id;
	}


}

package ch.hsr.dbs2.jpa_exercise.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * The persistent class for the bankcustomer database table.
 *
 */

@Entity
public class BankCustomer {
	
	@ManyToMany(mappedBy = "customers", fetch = FetchType.EAGER)
	private Collection<BankManager> managers = new ArrayList<>();
	
	@OneToMany
	@JoinColumn(name ="Account_CustomerId", referencedColumnName = "customerid")
	private Collection<BankAccount> accounts = new ArrayList<>();
	
	@OneToOne(optional = true)
	@JoinColumn(name = "Customer_AddressId")
	public Address address;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerid;
	public String name;
	public Date birthDate;
	
	public void addManager(BankManager m){
		managers.add(m);
		if(!m.getCustomers().contains(this)){
			m.getCustomers().add(this);
		}
	}
	
	public void removeManager(BankManager m){
		managers.remove(m);
		if(m.getCustomers().contains(this)){
			m.getCustomers().remove(this);
		}
	}
	
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

	public Collection<BankManager> getManagers() {
		return managers;
	}

	public void setManagers(Collection<BankManager> managers) {
		this.managers = managers;
	}

	public Collection<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(Collection<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "\t" + customerid + "\t" + name;
	}
}

package ch.hsr.dbs2.jpa_exercise.model;

import javax.persistence.*;

/**
 * The persistent class for the bankaccount database table.
 *
 */

@Entity
public class BankAccount {
	
	@Id
	private long id;
	public double balance;
	public Currency currency;
	public BankCustomer customer;
	
	public BankAccount(){}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
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

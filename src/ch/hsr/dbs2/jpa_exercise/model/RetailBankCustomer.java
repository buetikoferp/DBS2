package ch.hsr.dbs2.jpa_exercise.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Retail")
public class RetailBankCustomer extends BankCustomer{
	
	private double annualFees;
	
	public RetailBankCustomer(){}

	public double getAnnualFees() {
		return annualFees;
	}

	public void setAnnualFees(double annualFees) {
		this.annualFees = annualFees;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\t" + annualFees;
	}

}

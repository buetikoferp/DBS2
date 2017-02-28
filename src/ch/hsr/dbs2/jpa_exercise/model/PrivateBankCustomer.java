package ch.hsr.dbs2.jpa_exercise.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Private")
public class PrivateBankCustomer extends BankCustomer{
	
	public int bonusProgrammId;
	
	public PrivateBankCustomer(){}
	
	public int getBonusProgrammId() {
		return bonusProgrammId;
	}

	public void setBonusProgrammId(int bonusProgrammId) {
		this.bonusProgrammId = bonusProgrammId;
	}	
	
	@Override
	public String toString() {
		return super.toString()	+ bonusProgrammId;
	}
}

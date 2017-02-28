package ch.hsr.dbs2.jpa_exercise.main;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

import ch.hsr.dbs2.jpa_exercise.model.Address;
import ch.hsr.dbs2.jpa_exercise.model.BankAccount;
import ch.hsr.dbs2.jpa_exercise.model.BankCustomer;
import ch.hsr.dbs2.jpa_exercise.model.BankManager;
import ch.hsr.dbs2.jpa_exercise.model.Currency;

public class TheBank {

	private static EntityManagerFactory factory;

	//Uebung 01
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory("Bank");
		EntityManager em = factory.createEntityManager();
		try {
			
			// Uebung 01
			openAccount("Philipp", new Date(new GregorianCalendar(1992,12-1,12).getTimeInMillis()));
			openAccount("Anthony", new Date(new GregorianCalendar(1992,12-1,12).getTimeInMillis()));
//			transfer(101, 102, 100);
//			printAddresses(em);
//			printBankAccounts(em);
//			printBankCustomers(em);
//			printBankManagers(em);
			
			//Uebung 02
			testBidirectionalRelations();
		} finally {
			em.close();
		}
	}
	
	private static void transfer(long fromId, long toId, double balance){
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			BankAccount from = em.find(BankAccount.class, fromId);
			BankAccount to = em.find(BankAccount.class, toId);
			from.balance -= balance;
			to.balance += balance;
			em.getTransaction().commit();
			System.out.println("Transaction from:" + from.customer.name + " to: " + to.customer.name + " is done");
			
		}catch(Exception e){
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
	}
	
	private static void closeAccount(BankAccount close){
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			close = em.find(BankAccount.class, 1L);
			em.remove(close);
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
	}
	
	private static void openAccount(String name, Date birthDate){
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			BankCustomer customer = new BankCustomer();
			customer.setName(name);
			customer.setBirthDate(birthDate);
			
			BankAccount ba = new BankAccount();
			ba.setBalance(200);
			ba.setCurrency(Currency.CHF);
			ba.setCustomer(customer);
			customer.getAccounts().add(ba);
			em.persist(ba);
			em.persist(customer);
			em.getTransaction().commit();
		}finally{
			em.close();
		}
	}
	
	//Uebung 02
	
	public static void testBidirectionalRelations(){
		EntityManager em = factory.createEntityManager();
		
		try{
			
			em.getTransaction().begin();
			BankCustomer b1 = em.find(BankCustomer.class, 1L);
			BankManager newManager = new BankManager();
			newManager.setName("Adrian Fröhlich");
			b1.addManager(newManager);
			
			if(newManager.getCustomers().contains(b1)){
				System.out.println("Bidirektionale Realtion synchronisiert korrekt");
			}else{
				System.out.println("Unsynchronisierter Zustand");
			}
			
			em.persist(newManager);
			em.persist(b1);
			em.getTransaction().commit();
			
			printBankCustomers(em);
			printBankManagers(em);
			
		}catch(Exception e){
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
	}

	private static void printAddresses(EntityManager em) {
		Query query = em.createQuery("select a from Address a");
		@SuppressWarnings("unchecked")
		List<Address> list = query.getResultList();
		for (Address b : list) {
			System.out.println(b);
		}
	}

	private static void printBankAccounts(EntityManager em) {
		Query query = em.createQuery("select a from BankAccount a");
		@SuppressWarnings("unchecked")
		List<BankAccount> list = query.getResultList();
		for (BankAccount b : list) {
			System.out.println(b);
		}
	}

	private static void printBankCustomers(EntityManager em) {
		Query query = em.createQuery("select a from BankCustomer a");
		@SuppressWarnings("unchecked")
		List<BankCustomer> list = query.getResultList();
		for (BankCustomer b : list) {
			System.out.println(b);
		}
	}

	private static void printBankManagers(EntityManager em) {
		Query query = em.createQuery("select a from BankManager a");
		@SuppressWarnings("unchecked")
		List<BankManager> list = query.getResultList();
		for (BankManager b : list) {
			System.out.println(b);
		}
	}

}

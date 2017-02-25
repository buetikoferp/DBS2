package ch.hsr.dbs2.jpa_exercise.main;

import java.util.List;

import javax.persistence.*;

import ch.hsr.dbs2.jpa_exercise.model.Address;
import ch.hsr.dbs2.jpa_exercise.model.BankAccount;
import ch.hsr.dbs2.jpa_exercise.model.BankCustomer;
import ch.hsr.dbs2.jpa_exercise.model.BankManager;

public class TheBank {

	private static EntityManagerFactory factory;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory("Bank");
		EntityManager em = factory.createEntityManager();
		try {
			printAddresses(em);
			printBankAccounts(em);
			printBankCustomers(em);
			printBankManagers(em);
		} finally {
			em.close();
		}
	}
	
	private void transfer(BankAccount from, BankAccount to, double balance){
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
			from = em.find(BankAccount.class, from.getId());
			to = em.find(BankAccount.class, to.getId());
			from.balance -= balance;
			to.balance += balance;
			em.getTransaction().commit();
			
		}catch(Exception e){
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
	}
	
	private void closeAccount(BankAccount close){
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
	
	private void openAccount(String name, String birthDate){
		EntityManager em = factory.createEntityManager();
		try{
			BankCustomer bc = new BankCustomer();
			bc.setName(name);
			bc.setBirthDate(birthDate);
			
			BankAccount ba = new BankAccount();
			ba.setBalance(0);
			bc.getAccounts().add(ba);
			em.persist(ba);
			em.persist(bc);
			em.getTransaction().commit();
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

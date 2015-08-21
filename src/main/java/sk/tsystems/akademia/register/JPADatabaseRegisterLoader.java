package sk.tsystems.akademia.register;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class JPADatabaseRegisterLoader implements RegisterLoader {

	private EntityManagerFactory factory;
	private EntityManager em;
	private EntityTransaction tr;
	boolean init = false;
	
	/**
	 * Constructor for loader
	 */
	public JPADatabaseRegisterLoader() {
		try {
			this.factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
			this.em = factory.createEntityManager();
			this.tr = em.getTransaction();
			this.init = true;
		} catch (PersistenceException e) {
			this.init = false;
			close();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see register.RegisterLoader#save(register.Register)
	 */
	@Override
	public void save(Register register) {
		if (init) {
			Person p;
			for (int i = 0; i < register.getCount(); i++) {
				p = register.getPerson(i);
				tr.begin();
				em.persist(p);
				tr.commit();
			}
		} else {
			System.out.println("You need to create database manually to save with JPA");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.RegisterLoader#load()
	 */
	@Override
	public Register load() {
		if (init) {
			Register register = new ListRegister();
			tr.begin();
			List<Person> persons = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
			for (Person p : persons){
				register.addPerson(p);
			}
			tr.commit();
			if (register.getCount() == 0) {
				return null;
			} else {
				return register;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Close Entity manager and factory manager after working with database
	 */
	public void close() {
		if (em != null) {
			em.close();
		}
		if (factory != null) {
			factory.close();
		}
	}
	
}

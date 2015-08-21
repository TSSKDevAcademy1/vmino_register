package sk.tsystems.akademia.register;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListRegister implements Register, Iterable<Person>, Serializable {

	/**
	 * 
	 */
	private List<Person> persons = new ArrayList<Person>();

	
	@Override
	public Iterator<Person> iterator() {
		return persons.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getCount()
	 */
	@Override
	public int getCount() {
		return persons.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getSize()
	 */
	@Override
	public int getSize() {
		return Integer.MAX_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getPerson(int)
	 */
	@Override
	public Person getPerson(int index) {
		return persons.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#addPerson(register.Person)
	 */
	@Override
	public void addPerson(Person person) {
		persons.add(person);

		updateList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getMaxLengthName()
	 */
	@Override
	public int getMaxLengthName() {
		int maxLength = 0;
		int length;
		Iterator<Person> iterator = persons.iterator();
		while (iterator.hasNext()) {
			length = iterator.next().getName().length();
			if (length > maxLength) {
				maxLength = length;
			}
		}
		return maxLength;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getMaxLengthNumber()
	 */
	@Override
	public int getMaxLengthNumber() {
		int maxLength = 0;
		int length;
		Iterator<Person> iterator = persons.iterator();
		while (iterator.hasNext()) {
			length = iterator.next().getPhoneNumber().length();
			if (length > maxLength) {
				maxLength = length;
			}
		}
		return maxLength;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#findPersonByName(java.lang.String)
	 */
	@Override
	public Person findPersonByName(String name) {
		return persons.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#findPersonByPhoneNumber(java.lang.String)
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		return persons.stream().filter(s -> s.getPhoneNumber().equals(phoneNumber)).findFirst().orElse(null);


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#findPersonByBoth(java.lang.String, java.lang.String)
	 */
	@Override
	public Person findPersonByBoth(String name, String phoneNumber) {
		Iterator<Person> iterator = persons.iterator();
		while (iterator.hasNext()) {
			Person p = iterator.next();
			if (p.getName().equals(name)) {
				if (p.getPhoneNumber().equals(phoneNumber)) {
					return p;
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#removePerson(register.Person)
	 */
	@Override
	public void removePerson(Person person) {
		persons.remove(person);
		updateList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#updateList()
	 */
	public void updateList() {
		Collections.sort(persons);
	}
}


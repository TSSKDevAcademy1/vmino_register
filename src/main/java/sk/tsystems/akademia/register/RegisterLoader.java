package sk.tsystems.akademia.register;

public interface RegisterLoader {

	/**
	 * Saves register into file / database
	 * 
	 * @param register
	 */
	void save(Register register);

	/**
	 * Creates new ListRegister and loads it from file / database
	 * 
	 * @return
	 */
	Register load();

}

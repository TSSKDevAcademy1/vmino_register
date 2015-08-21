package sk.tsystems.akademia.register;

/**
 * Created by Vladislav Mino 7.8.2015
 */
public class App {

	/**
	 * Main method for starting register app
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Looking for registers ...");
		ConsoleUI ui = new ConsoleUI();
		ui.run();
	}
}

package application;

/**
 * The Program class serves as the entry point for the banking system application.
 * It initializes and starts the menu interface.
 */
public class Program {
	
	/**
	 * The main method initializes and starts the menu interface.
	 */
	public static void main() {
		Menu menu = new Menu();
		menu.start();
	}
}
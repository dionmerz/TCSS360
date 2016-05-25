package TCSS360;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * This is the main driver program for the Conference System program
 * and its UI.
 * 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 1.0 5/8/2016
 */
public class Main {


	private static TerminalUserInterface myUI;

	/**
	 * Runs the SMART Scientific Manuscripts Are Reviewed in Terminal program
	 * 
	 * @param theargs
	 */
	public static void main(String[] theargs) {

		try {
			FileInputStream fileIn = new FileInputStream("UI.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			myUI = (TerminalUserInterface) in.readObject();
			
			fileIn.close();
			in.close();
			
			
			
		} catch (FileNotFoundException e) {
			myUI = new TerminalUserInterface();
			
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			
		} catch (IOException e) {
			System.out.println(e);
		}
		
		myUI.loginRegisterMenu();
	
		exit();


	}
	

	/**
	 * UI exit menu
	 */
	public static void exit() {
		System.out.println("You selected exit.");

		try {
			FileOutputStream fileOut = new FileOutputStream("UI.ser");
			ObjectOutputStream output = new ObjectOutputStream(fileOut);
			output.writeObject(myUI);

			output.close();
			fileOut.close();

		} catch (IOException e) {
			
		}
	}
}

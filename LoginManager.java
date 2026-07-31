package slc;
import java.util.Scanner;

public class LoginManager {
	//This is for local testing; later, the system will be improved
	private final String userName = "admin123";
	private final String 	password = "Admin";
	
	Scanner scnr = new Scanner(System.in);
	public boolean verifyAdmin() {
		boolean isAdmin = false;
		String inputUserName = "";
		String inputPassword = "";
		System.out.println("Login Menu: ");
		System.out.println("Username? ");
		inputUserName = scnr.nextLine();
		System.out.println("Password? ");
		inputPassword = scnr.nextLine();
		
		if(inputUserName.equals(userName) && inputPassword.equals(password)) {
			System.out.println("Login successful.");
			isAdmin = true;
		}else {
			System.out.println("Wrong credentials");
		}


		return isAdmin;
	}

}

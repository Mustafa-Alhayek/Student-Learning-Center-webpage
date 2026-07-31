package slc;
import java.util.ArrayList;
import java.util.Scanner;

public class SLCapp {
	
	
	static Scanner userInput = new Scanner(System.in);		
	public static String fileName = "sessions.csv";
	SearchManager searchEngine = new SearchManager();
	
	public static void main(String[] args) {

		SLCapp program = new SLCapp();
		SessionFileManager csvManagement = new SessionFileManager();
		ArrayList<Session> sessions = csvManagement.loadSessionsFromCSV(fileName);	
		LoginManager authentication = new LoginManager();
		System.out.println("Hello, Welcome to the Student Learning Center (SLC) website. Proceed to login: (input numbers)");
		
		String input = "";
		boolean isAdmin = false;
		while(!input.equals("3")) {
			program.printLoginMenu();
			input = userInput.nextLine().toLowerCase().trim();
			
			if(input.equals("1")) {
				program.studentMenuLogic(sessions);
			}else if(input.equals("2")) {
				isAdmin = authentication.verifyAdmin();
				if(isAdmin) {
				program.adminMenuLogic(sessions);
				}
			}else if(input.equals("3")) {
			System.out.println("Goodbye!");
			}else {
			System.out.println("Invalid input! Try again.");
		}
	}	
}
	
	public void printLoginMenu() {
		System.out.println("1. Student");
		System.out.println("2. Admin");
		System.out.println("3. Exit");
	}
	
	public void adminMenu() {
		System.out.println("1. Add session");
		System.out.println("2. Edit session");
		System.out.println("3. Delete session");
		System.out.println("4. View all sessions");
		System.out.println("5. View weekly schedule");
		System.out.println("6. Search menu");
		System.out.println("7. Back to main menu");
	}
	
	public void studentMenu() {
		System.out.println("1. View all sessions");
		System.out.println("2. View weekly schedule");
		System.out.println("3. Search menu");
		System.out.println("4. Back to main Menu");
	}
	
	public void printSearchMenu() {
		System.out.println("1. Search by keyword");
		System.out.println("2. Filter by day");
		System.out.println("3. Filter by session type");
		System.out.println("4. Filter by day and course");
		System.out.println("5. Back to main menu");	
	}
	
	public void adminMenuLogic(ArrayList<Session> sessions) {
		String input = "";
		SessionViewManager viewManager = new SessionViewManager();
		AdminPage admin = new AdminPage();
		SessionFileManager csvManagement = new SessionFileManager();

		while(!input.equalsIgnoreCase("7")) {

			adminMenu();
			input = userInput.nextLine().toLowerCase().trim();
			
			if(input.equals("1")) {
				admin.addSession(sessions);
				csvManagement.writeToCSVFile(fileName, sessions);
			}else if(input.equals("2")) {
				admin.editSession(sessions);
				csvManagement.writeToCSVFile(fileName, sessions);
			}else if(input.equals("3")) {
				admin.deleteSession(sessions);
				csvManagement.writeToCSVFile(fileName, sessions);
			}else if(input.equals("4")) {
				viewManager.viewAllSessions(sessions);
			}else if(input.equals("5")) {
				viewManager.viewWeeklyCalendar(sessions);
			}else if(input.equals("6")) {
				searchEngine.searchMenuLogic(sessions);
			}else if(input.equals("7")) {
				System.out.println("Back to main menu");
			}else {
				System.out.println("Invalid input! Try again.");
			}
			
			
		}
	}
	
	public void studentMenuLogic(ArrayList<Session> sessions) {
		String input = "";
		SessionViewManager viewManager = new SessionViewManager();

		while(!input.equalsIgnoreCase("4")) {

			studentMenu();
			input = userInput.nextLine().toLowerCase().trim();
			
			if(input.equals("1")) {
				viewManager.viewAllSessions(sessions);
			}else if(input.equals("2")) {
				viewManager.viewWeeklyCalendar(sessions);
			}else if(input.equals("3")) {
				searchEngine.searchMenuLogic(sessions);
			}else if(input.equals("4")) {
				System.out.println("Returning to main menu...");
			}else {
				System.out.println("Invalid input! Try again.");
			}
			
			
		}
	}
	
	
	
	/*
	
	public String askForNonBlankInput(String blankInput) {
		while(blankInput.isBlank()) {
			System.out.println("Don't leave it blank. Try again");
			blankInput = userInput.nextLine();
		}	
		return blankInput.trim();
	}
	
	public String checkForValidDay(String dayInput) {
		
		while(true) {
			try {
				DayOfWeek day = DayOfWeek.valueOf(dayInput.trim().toUpperCase());
				String dayName = day.toString().toLowerCase();
					return dayName.toUpperCase().charAt(0) + (dayName.substring(1));
				
				}catch(IllegalArgumentException e) {
					System.out.println("Input is not a day of the week. Try again");
					System.out.println("Please only write valid week days");
					dayInput = userInput.nextLine().trim();
				}
		}

	}
	
	
	
	/*public void searchBySessionType(ArrayList<Session> sessions) {
	   System.out.println("What session type you would like to search?");
		String sessionTypeInput = userInput.nextLine().toLowerCase().trim();
		boolean found = false;

		for(int i = 0; i < sessions.size(); i++) {
			if(sessions.get(i).getSessionType().toLowerCase().contains(sessionTypeInput)) {
				found = true;
				System.out.println(sessions.get(i).toShortString());
			}
			
		}
		if(!found) {
			System.out.println("No matches found");
		}
	}
	
	public void searchKeyword(ArrayList<Session> sessions) {
		if(sessions.size() == 0 ) {
			System.out.println("No sessions avaliable.");
			return;
		}
		SessionViewManager viewManager = new SessionViewManager();
		System.out.println("Type a keyword you would like to search: ");
		String userKeyword = userInput.nextLine().toLowerCase().trim();
		userKeyword = askForNonBlankInput(userKeyword);
		viewManager.sortSessionsByDayAndTime(sessions);
		int resultsFound = 0;
		
		for(int i = 0; i <  sessions.size(); i++) {
			if(sessions.get(i).toString().toLowerCase().contains(userKeyword)) {
				resultsFound++;
				System.out.println(sessions.get(i).toShortString());
			}
		}
		if(resultsFound > 0) {
			System.out.println( resultsFound +" matches found");
		}else {
			System.out.println("No matches found");
		}
	}
	
	public void searchByDayandCourse(ArrayList<Session> sessions) {
		if(sessions.size() == 0 ) {
			System.out.println("No sessions avaliable.");
			return;
		}
		
		System.out.println("What day are you looking for?");
		String weekDay = userInput.nextLine().toLowerCase().trim();
		weekDay = askForNonBlankInput(weekDay);
		weekDay = checkForValidDay(weekDay);
		
		System.out.println("What course are you looking for?");
		String courseInput = userInput.nextLine().toLowerCase().trim();
		courseInput = askForNonBlankInput(courseInput);

		SessionViewManager viewManager = new SessionViewManager();
		viewManager.sortSessionsByDayAndTime(sessions);
		
		int resultsFound = 0;
		
		for(int i = 0; i < sessions.size(); i++) {
			if(sessions.get(i).getSessionDay().toLowerCase().equalsIgnoreCase(weekDay) 
				&& sessions.get(i).getCourseCode().toLowerCase().contains(courseInput)) {
				System.out.println(sessions.get(i).toShortString());
				resultsFound++;
			} 
			
		}	

		
		if(resultsFound > 0) {
			System.out.println( resultsFound +" matches found");
		}else {
			System.out.println("No matches found");
		}
	
	}
	
	public void filterByDay(ArrayList<Session> sessions) {
		System.out.println("What day are you looking for?");
		String weekDay = userInput.nextLine().toLowerCase().trim();
		boolean found = false;
		
		for(int i = 0; i < sessions.size(); i++) {
			if(sessions.get(i).getSessionDay().toLowerCase().contains(weekDay)) {
				found = true;
				System.out.println(sessions.get(i).toShortString());
			} 
			
		}
		if(!found) {
			System.out.println("No matches found");
		}
	}
	
	
	/*
	public void searchByCourse(ArrayList<Session> sessions) {
		System.out.println("What course you would like to search?");
		String courseInput = userInput.nextLine().toLowerCase().trim();
		boolean found = false;
		for(int i = 0; i < sessions.size(); i++) {
			if(sessions.get(i).getCourseCode().toLowerCase().contains(courseInput)) {
				found = true;
				System.out.println(sessions.get(i).toString());
			}
			
		}
		if(!found) {
			System.out.println("No matches found");
		}
	}
	
	public void searchBySubject(ArrayList<Session> sessions) {
		System.out.println("What subject you would like to search?");
		String subjectInput = userInput.nextLine().toLowerCase().trim();
		boolean found = false;

		for(int i = 0; i < sessions.size(); i++) {
			if(sessions.get(i).getSubject().toLowerCase().contains(subjectInput)) {
				found = true;
				System.out.println(sessions.get(i).toString());
			}
			
		}
		if(!found) {
			System.out.println("No matches found");
		}
	}
	*/
}

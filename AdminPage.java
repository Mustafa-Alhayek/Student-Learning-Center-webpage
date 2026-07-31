package slc;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminPage {
	Scanner userInput = new Scanner(System.in);		
	SLCapp mainMenu = new SLCapp();
	SessionViewManager viewManager = new SessionViewManager();
	InputValidation checkInput = new InputValidation();

	public void addSession(ArrayList<Session> sessions) {
		
		System.out.println("Enter subject: ");
		String subject = userInput.nextLine();
		subject = checkInput.askForNonBlankInput(subject);
		
		System.out.println("Enter course code: ");
		String courseCode = userInput.nextLine();
		courseCode = checkInput.askForNonBlankInput(courseCode);

		System.out.println("Enter session type: ");
		String sessionType = userInput.nextLine();
		sessionType = checkInput.askForNonBlankInput(sessionType);

		System.out.println("Enter session day: ");
		String sessionDay = userInput.nextLine();
		sessionDay = checkInput.askForNonBlankInput(sessionDay);
		sessionDay = checkInput.checkForValidDay(sessionDay);
		
		
		LocalTime[] times = checkInput.checkForCorrectTime();
		LocalTime startTime = times[0];		
		LocalTime endTime = times[1];
		
		
	
		System.out.println("Enter session location: ");
		String location = userInput.nextLine();
		location = checkInput.askForNonBlankInput(location);
		
		System.out.println("Enter session leaderName: ");
		String leaderName = userInput.nextLine();
		leaderName = checkInput.askForNonBlankInput(leaderName);

		System.out.println("Enter session description: ");
		String description = userInput.nextLine();
		description = checkInput.askForNonBlankInput(description);
		
		Session session = new Session(subject, courseCode, sessionType, 
									  sessionDay,startTime, endTime,
									  location, leaderName, description);
		
		sessions.add(session);
		
	}
	/*
	private LocalTime[] checkForCorrectTime() {
		while(true) {
			System.out.println("Enter session start time (HH:mm): ");
			String inputStartTime = userInput.nextLine().trim();
			System.out.println("Enter session end time (HH:mm): ");
			String inputEndTime = userInput.nextLine().trim();
			try {
			LocalTime startTime = LocalTime.parse(inputStartTime);
			LocalTime endTime = LocalTime.parse(inputEndTime);
			if(startTime.isBefore(endTime)) {
				LocalTime[] sessionTimes = {startTime, endTime};
				return sessionTimes;
			}else {
				System.out.println("Start time needs to be before end time");
			}
			}catch(DateTimeParseException e) {
				System.out.println("Wrong time Input. Please ensure input is in HH:mm format");
			}
		}
	}*/
	
		
	private void printEditMenu() {
		System.out.println("What Information you would like to edit? ");
		System.out.println("1. Subject");
		System.out.println("2. Course code");
		System.out.println("3. Session type");
		System.out.println("4. Session day");
		System.out.println("5. Session times");
		System.out.println("6. Session location");
		System.out.println("7. Leader name");
		System.out.println("8. Description");
		System.out.println("9. Done editing");

	}
	
	private boolean editSelectedField(String infoToEdit, Session session) {
		String newInfo =  "";
		boolean edited = false;
		if(infoToEdit.equals("1")) {
			System.out.println("What would you like the new subject to be? ");
			newInfo = userInput.nextLine();
			newInfo = checkInput.askForNonBlankInput(newInfo);
			session.setSubject(newInfo);
			edited = true;
			
		}else if(infoToEdit.equals("2")) {
			System.out.println("What would you like the new Course code to be? ");
			newInfo = userInput.nextLine();
			newInfo = checkInput.askForNonBlankInput(newInfo);
			session.setCourseCode(newInfo);
			edited = true;
			
		}else if(infoToEdit.equals("3")) {
			System.out.println("What would you like the new Session type to be? ");
			newInfo = userInput.nextLine();
			newInfo = checkInput.askForNonBlankInput(newInfo);
			session.setSessionType(newInfo);
			edited = true;
			
		}else if(infoToEdit.equals("4")) {
			System.out.println("What would you like the new Session day to be? ");
			newInfo = userInput.nextLine();
			newInfo = checkInput.askForNonBlankInput(newInfo);
			newInfo = checkInput.checkForValidDay(newInfo);
			session.setSessionDay(newInfo);
			edited = true;

		}else if(infoToEdit.equals("5")) {
			System.out.println("What would you like the new session times to be?");
			  LocalTime[] times = checkInput.checkForCorrectTime();
			  LocalTime startTime = times[0];		
		      LocalTime endTime = times[1];
		      session.setStartTime(startTime);
		      session.setEndTime(endTime);
			  edited = true;

		}else if(infoToEdit.equals("6")) {
			System.out.println("What would you like the new Session location to be? ");
			newInfo = userInput.nextLine();
			newInfo = checkInput.askForNonBlankInput(newInfo);
			session.setLocation(newInfo);
			edited = true;

		}else if(infoToEdit.equals("7")) {
			System.out.println("What would you like the new Leader name to be? ");
			newInfo = userInput.nextLine();
			newInfo = checkInput.askForNonBlankInput(newInfo);
			session.setLeaderName(newInfo);
			edited = true;

		}else if(infoToEdit.equals("8")){
			System.out.println("What would you like the new Description to be? ");
			newInfo = userInput.nextLine();
			newInfo = checkInput.askForNonBlankInput(newInfo);
			session.setDescription(newInfo);
			edited = true;

		}else {
			edited = false;
			System.out.println("Wrong input.");
		}
		return edited;
	}
	
	public void editSession(ArrayList<Session> sessions) {
		if(sessions.size() == 0 ) {
			System.out.println("No sessions avaliable to edit.");
			return;
		}
		boolean validIndex = false;
		String sessionToedit = "";
		String infoToEdit = "";
		String runningEdit = "";
		System.out.println("Choose which session you would like to edit: (input number)");
		viewManager.viewAllSessions(sessions);
		try {
		sessionToedit = userInput.nextLine().trim();
		
		int infoIndex = Integer.parseInt(sessionToedit);
		if(infoIndex <= sessions.size() && infoIndex >= 1) {
			validIndex = true;
		}
		if(validIndex) {
			while(!runningEdit.equals("9")) {
			printEditMenu();
			infoToEdit = userInput.nextLine().trim();
			boolean edit = editSelectedField(infoToEdit, sessions.get(infoIndex - 1));
			if(edit) {
				System.out.println("Edited successfully ");
			}else {
				System.out.println("Try again!");
			}
			System.out.println("Would you like to Edit other information in this session?" +  "\nIf yes type anything, if not type 9 .");
			runningEdit = userInput.nextLine().trim();
			}
		}else if(!validIndex) {
			System.out.println("Invalid Index, try again.");
		}
		
		}catch(NumberFormatException e) {
			System.out.println("Wrong type of input.");
		}
	}

	public void deleteSession(ArrayList<Session> sessions) {
		if(sessions.size() == 0 ) {
			System.out.println("No sessions available to delete.");
			return;
		}
		boolean validIndex = false;
		String sessionTodelete = "";
		System.out.println("Choose which session you would like to delete: (input number)");
		viewManager.viewAllSessions(sessions);
		try {
		sessionTodelete = userInput.nextLine().trim();
		
		int infoIndex = Integer.parseInt(sessionTodelete);
		if(infoIndex <= sessions.size() && infoIndex >= 1) {
			validIndex = true;
		}
		if(validIndex) {
			sessions.remove(infoIndex - 1);			
			System.out.println("Deleted successfully");
		}else if(!validIndex) {
			System.out.println("Invalid Index, try again.");
		}
		
		}catch(NumberFormatException e) {
			System.out.println("Wrong type of input.");
		}
		
	}

}

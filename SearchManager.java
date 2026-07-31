package slc;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchManager {
	Scanner userInput = new Scanner(System.in);		
	InputValidation checkInput = new InputValidation();
	
	public void searchBySessionType(ArrayList<Session> sessions) {
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
		userKeyword = checkInput.askForNonBlankInput(userKeyword);
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
		weekDay = checkInput.askForNonBlankInput(weekDay);
		weekDay = checkInput.checkForValidDay(weekDay);
		
		System.out.println("What course are you looking for?");
		String courseInput = userInput.nextLine().toLowerCase().trim();
		courseInput = checkInput.askForNonBlankInput(courseInput);

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
	
	public void printSearchMenu() {
		System.out.println("1. Search by keyword");
		System.out.println("2. Filter by day");
		System.out.println("3. Filter by session type");
		System.out.println("4. Filter by day and course");
		System.out.println("5. Back to main menu");	
	}
	
	public void searchMenuLogic(ArrayList<Session> sessions) {
		String searchInput = "";
		while(!searchInput.equalsIgnoreCase("5")) {
			
		printSearchMenu();
		searchInput = userInput.nextLine().toLowerCase().trim();
		if(searchInput.equals("1")){
			searchKeyword(sessions);
		}else if(searchInput.equals("2")) {
			filterByDay(sessions);
		}else if(searchInput.equals("3")) {
			searchBySessionType(sessions);
		}else if(searchInput.equals("4")) {
			searchByDayandCourse(sessions);
		}else if(searchInput.equals("5")) {
			System.out.println("Returning to main menu...");
		}else {
			System.out.println("Invalid input! Try again.");
		}
		}
	}
}

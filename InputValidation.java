package slc;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidation {
	
	Scanner userInput = new Scanner(System.in);		

	public LocalTime[] checkForCorrectTime() {
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
	}
	
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
}

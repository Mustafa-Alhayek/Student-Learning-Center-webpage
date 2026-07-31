package slc;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;

public class SessionViewManager {
	
	public void viewAllSessions(ArrayList<Session> sessions) {
		if(sessions.size() == 0) {
			System.out.println("There are no sessions available");
		}
		sortSessionsByDayAndTime(sessions);
		for(int i = 0; i < sessions.size(); i++) {
			System.out.print((i+1) + ". " + sessions.get(i).toString());
		}
	}
	
	public void sortSessionsByDayAndTime(ArrayList<Session> sessions) {
			sessions.sort(
			    Comparator
		        .comparingInt((Session session) -> getDayOrder(session.getSessionDay()))
		        .thenComparing((Session session) -> session.getStartTime())
		);	
		} 
	
	private int getDayOrder(String dayName) {
		int weekDay = DayOfWeek.valueOf(dayName.trim().toUpperCase()).getValue();
		return weekDay;
	}
	//I would replace this with a HashMap later when I gain a better understanding of it so I can reduce time complexity
	public void viewWeeklyCalendar(ArrayList<Session> sessions) {
		if(sessions.size() == 0) {
			System.out.println("There are no sessions available");
		}
		
		String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		sortSessionsByDayAndTime(sessions);
		for(int i = 0; i < days.length; i++) {
			boolean found = false;
			System.out.println("");
			System.out.println(days[i] + ": ");
			for(int j = 0; j < sessions.size(); j++) {
				if(sessions.get(j).getSessionDay().equalsIgnoreCase(days[i])) {
					found = true;
					System.out.println(sessions.get(j).toShortString());
				}
			}
			if(!found) {
				System.out.println("No Session available");
			}
		}
	}

}

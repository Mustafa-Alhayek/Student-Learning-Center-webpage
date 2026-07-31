package slc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;

public class SessionFileManager {
	
	public ArrayList<Session> loadSessionsFromCSV(String filename) {
		ArrayList<Session> sessions = new ArrayList<Session>();

		try(BufferedReader br = new BufferedReader(new FileReader(filename))){
			String line = "";
			
			while((line = br.readLine()) != null) {
				Session session = csvToSession(line);
				sessions.add(session);
			}
			
		}catch(FileNotFoundException e) {
			System.out.print("File Not found!");
			e.printStackTrace();
		}catch(IOException e) {
			System.out.print("File Couldn't be closed!");
			e.printStackTrace();
		}
		return sessions;

	}
	
	public String sessionToCSV(Session session) {
		String csvLine = session.getSubject() + ", " + session.getCourseCode() + ", " + 
	session.getSessionType() + ", " + session.getSessionDay() + ", " + session.getStartTime() + ", " +
	session.getEndTime() + ", "	+ session.getLocation() + ", " + session.getLeaderName() + ", " +
	session.getDescription();
		
		return csvLine;
}
	public Session csvToSession(String line) {
		String[] info = line.split(",");
		Session session = new Session(info[0].trim(), info[1].trim(), info[2].trim(),
				info[3].trim(), LocalTime.parse(info[4].trim()), LocalTime.parse(info[5].trim()), 
				info[6].trim(), info[7].trim(), info[8].trim());
		return session;
		
}
	
	public void writeToCSVFile(String fileName, ArrayList<Session> sessions) {
		try(PrintWriter pw = new PrintWriter(fileName)){
			
			for(int i = 0; i< sessions.size(); i++) {
				pw.println(sessionToCSV(sessions.get(i)));
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("Error while finding/creating the file");
			e.printStackTrace();
		}
	}
	
	
}

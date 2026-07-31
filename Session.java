package slc;
import java.time.LocalTime;
public class Session {
	
	private String subject;
	private String courseCode;
	private String sessionType;
	private String sessionDay;
	private LocalTime startTime;
	private LocalTime endTime;
	private String location;
	private String leaderName;
	private String description;
	
	public Session(String subject, String courseCode, String sessionType,
			String sessionDay, LocalTime startTime, LocalTime endTime,
			String location, String leaderName,String description){
		
		this.subject = subject;
		this.courseCode = courseCode;
		this.sessionType = sessionType;
		this.sessionDay = sessionDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.leaderName = leaderName;
		this.description = description;
		
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getSessionType() {
		return sessionType;
	}
	
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}
	
	public String getSessionDay() {
		return sessionDay;
	}
	
	public void setSessionDay(String sessionDay) {
		this.sessionDay = sessionDay;
	}
	
	public LocalTime getStartTime(){
		return startTime;
	}
	
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	public LocalTime getEndTime(){
		return endTime;
	}
	
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLeaderName() {
		return leaderName;
	}
	
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		String sessionInfo = courseCode + " - " + sessionType + "\n" + 
							 "Subject: " + subject + "\n" +
							 "Day: " + sessionDay + "\n" +
							 "Time: " + startTime + " - " + endTime + "\n" +
							 "Location: " + location + "\n" +
							 "Leader: " + leaderName + "\n" +
							 "Description: " + description + "\n" ;
		return sessionInfo;
	}
	
	public String toShortString() {
		String shortenedInfo = courseCode + " | " + sessionType + " | " + startTime + " – " 
								+ endTime + " | " + location + " | " + "Leader: " + leaderName;
		return shortenedInfo;
	}
	
}

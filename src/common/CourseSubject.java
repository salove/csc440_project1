package common;

import java.util.ArrayList;
import java.util.List;

public class CourseSubject {

	private ArrayList<Topic> topics=new ArrayList<Topic>();
	private String idCode;
	private String name;
	private Key key;
	
	public CourseSubject(String idCode) {
		this.idCode=idCode;
		key=new Key(idCode);
	}
	
	public List<Topic> getTopics() {
		return topics;
	}
	
	public String getIdCode() {
		return idCode;
	}
	
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public Key getKey() {
		return key;
	}
}

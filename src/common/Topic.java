package common;

public class Topic {

	private String id;
	private String name;
	CourseSubject subject;
	Key key;
		
	
	public Topic(CourseSubject courseSubject, String id) {
		this.subject=courseSubject;
		this.id=id;
		key=new Key(courseSubject.getKey(), id);
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public Key getKey() {
		return key;
	}
}

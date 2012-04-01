package common;
import exception.*;


public class User {

	
	private String name="";
	private String userId="";
	private int role=0;
	private String pwHash="";
	private Key key;
	
	public static final int ROLE_ADMINISTRATOR=1;
	public static final int ROLE_INSTRUCTOR=2;
	public static final int ROLE_STUDENT=4;
	public static final int ROLE_TA=8;
	
		
	User(User user) {
		this.name=user.name;
		this.role=user.role;
		key=new Key(this.userId);
	}
	
	protected User(User user, int role) throws RoleException {
		if (!user.hasRole(role)) {
			throw new RoleException(user,getRoleName(role)); 
		}
		this.userId=user.userId;
		this.name=user.name;
		this.role=user.role;
		key=new Key(this.userId);
	}
	
	public boolean hasRole(int role) {
		return (0!=(this.role&role));
	}
	
	public void addRole(int role) {
		this.role|=role;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId=userId;
	}
	
	public String getPWHash() {
		return this.pwHash;
	}
	
	public void setPWHash(String hash) {
		this.pwHash=hash;
	}
	
	public Key getKey() {
	
		return key;
	}
	
	public int countRoles() {
		int roleCount=0;
		
		for (int bits=this.role; bits!=0; bits>>=1) {
			if (0!=(bits&1)) {
				roleCount++;
			}
		}
		return roleCount;
	}
	
	private static String getRoleName(int role) {
		switch(role) {
		case ROLE_ADMINISTRATOR:
			return "Administrator";
		case ROLE_INSTRUCTOR:
			return "Instructor";
		case ROLE_STUDENT:
			return "Student";
		case ROLE_TA:
			return "TA";
		}
		return "";
	}
	
	
}

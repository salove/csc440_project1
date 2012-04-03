package common;

public class Session implements StatusUpdate {
	private User user;
	private StatusUpdate status;
	
	public Session(User user, StatusUpdate status) {
		this.user=user;
		this.status=status;
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public void statusUpdate(String msg) {
		status.statusUpdate(msg);
		
	}
	
	
}

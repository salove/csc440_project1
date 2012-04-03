package ui;

import action.AddUserAction;
import action.TableActions;
import common.User;
import common.Utils;

public class AdministratorDialogue extends GeneralDialogue {

	static final String[] adminMenu=  
		{
		"Create a new user",
		"Drop database tables",
		"Create databse tables",
		"Exit"
		};

	Selection adminSelection=new Selection("Choose one of the following:", adminMenu);

	AdministratorDialogue(UI ui) {
		super(ui);
	}

	void showMainDialogue() {
		for (boolean doExit=false; !doExit; ) {

			switch(select(adminSelection)) {
			case 0:
				createNewUser();
				break;
			case 1:
				dropTables();
				break;
			case 2:
				createTables();
				break;
			case 3:
				doExit=true;
				break;

			}

		}

	}


	private void createTables() {
		TableActions a=new TableActions(ui.getSession());

		try {
			a.createTables();
			ui.statusUpdate("Tables created ok.");
		} catch (Exception e) {
			ui.statusUpdate("Table create failed: "+e.getMessage());
		}

	}

	private void dropTables() {
		TableActions a=new TableActions(ui.getSession());

		try {
			a.dropTables();
			ui.statusUpdate("Tables dropped ok.");
		} catch (Exception e) {
			ui.statusUpdate("Table drop failed: "+e.getMessage());
		}

	}

	void createNewUser() {
		ui.clear();
		ui.write("\nCreate new user\n");
		ui.write(" Enter user id (logon id): ");
		String userId=ui.readLine();

		ui.write(" Enter the name of the user: ");
		String name=ui.readLine();

		int role=0;
		ui.write(" Is this user an Administrator: ");
		role |= yesNo() ? User.ROLE_ADMINISTRATOR : 0;
		ui.write(" Is this user an Instructor: ");
		role |= yesNo() ? User.ROLE_INSTRUCTOR : 0;
		ui.write(" Is this user a TA: ");
		role |= yesNo() ? User.ROLE_TA : 0;
		ui.write(" Is this user a Student: ");
		role |= yesNo() ? User.ROLE_STUDENT : 0;


		String pw=null;
		for (boolean isValid=false; !isValid; ) {
			ui.write(" Enter password for this user: ");
			ui.setObfuscated(true);
			pw=ui.readLine();

			ui.write(" Enter password a second time for verification: ");
			ui.setObfuscated(true);
			String pw2=ui.readLine();

			if(pw.equals(pw2)) {
				isValid=true;
			} else {
				ui.write("\nPasswords do not match - Try again.\n");
			}
		}

		String pwHash=Utils.pwHash(pw);
		AddUserAction a=new AddUserAction(ui.getSession());
		try {
			a.addUser(userId, name, role, pwHash);
			ui.statusUpdate("User added ok.");
		} catch (Exception e) {
			ui.statusUpdate("Add user failed: "+e.getMessage());
		}

	}


}

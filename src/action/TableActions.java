package action;

import java.sql.SQLException;

import common.Session;
import common.Settings;

import db.Factory;
import exception.ConnectionFailedException;

public class TableActions {
	public TableActions(Session s) {
		
	}
	
	public void createTables() throws ConnectionFailedException, SQLException {
		Factory f=Factory.getInstance(Settings.checkUnitTest());
		f.createTables();
	}

	public void dropTables() throws ConnectionFailedException, SQLException {
		Factory f=Factory.getInstance(Settings.checkUnitTest());
		f.dropTables();
	}
}

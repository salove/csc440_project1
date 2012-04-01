package db;

import java.sql.SQLException;

public interface Tables {
	void dropTables() throws SQLException;
	void createTables() throws SQLException;
	
}

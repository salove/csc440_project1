package action;

import java.sql.SQLException;
import java.util.List;

import common.*;

import db.Factory;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public class ExerciseActions {
	public ExerciseActions(Session session) {
		
	}

	public List<Exercise> getForCourse(Course c) throws ConnectionFailedException, RecordNotFoundException, SQLException {
		Factory f=Factory.getInstance(Settings.checkUnitTest());
		return f.getExercises().getExercisesByCourse(c);
	}
}

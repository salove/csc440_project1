package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Exercise;
import common.ExerciseAttempt;
import common.Student;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import exception.RoleException;

public class UTExerciseAttempts implements ExerciseAttempts {
    
    private List<ExerciseAttempt> list=new ArrayList<ExerciseAttempt>();
    private static UTExerciseAttempts instance=null;
    private UTExerciseAttempts() {
        
    }
    
    public static ExerciseAttempts getInstance(Connection connection) {
        if (null==instance) {
            instance=new UTExerciseAttempts();
        }
        return instance;
    }

    @Override
    public void dropTables() {
        list.clear();

    }

    @Override
    public void createTables() {
        // Nothing to do for UT

    }

    @Override
    public void addExerciseAttempt(ExerciseAttempt exerciseAttempt) {
        list.add(exerciseAttempt);

    }

    @Override
    public List<ExerciseAttempt> getExerciseAttemptsForStudent(Student s) {
        List<ExerciseAttempt> retVal=new ArrayList<ExerciseAttempt>();
        for (ExerciseAttempt ea : list) {
            if (ea.getStudent()==s) {
                retVal.add(ea);
            }
        }
        return retVal;
    }

    @Override
    public List<ExerciseAttempt> getExerciseAttemptsForExercise(Exercise e) {
        List<ExerciseAttempt> retVal=new ArrayList<ExerciseAttempt>();
        for (ExerciseAttempt ea : list) {
            if (ea.getExercise()==e) {
                retVal.add(ea);
            }
        }
        return retVal;
    }

	@Override
	public List<ExerciseAttempt> getAll() throws SQLException,
			ConnectionFailedException, RecordNotFoundException, RoleException {
		return list;
	}

}

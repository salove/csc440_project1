package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.*;
import exception.ConnectionFailedException;
import exception.RecordNotFoundException;
import exception.RoleException;

public class UTQueries implements Queries {
	
	private static UTQueries instance=null;
	private Factory factory;
	
	private UTQueries() throws ConnectionFailedException { 
		factory=Factory.getInstance(Settings.checkUnitTest());
	}
	
	public static Queries getInstance(Connection c) throws ConnectionFailedException {
		if (null==instance) {
			instance=new UTQueries();
		}
		return instance;
	}

	@Override
	public List<Student> findStudentsThatScoredMaxOnFirstAttemptForExercise(
			Exercise e) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		//  Figure out what the max is
		int max=Integer.MIN_VALUE;
		ArrayList<Student> retVal=new ArrayList<Student>();
		
		List<ExerciseAttempt> eaList=factory.getExerciseAttempts().getExerciseAttemptsForExercise(e);
		for (ExerciseAttempt ea:eaList) {
			if (1== getExerciseAttemptNumber(ea)) {
				max=Math.max(max, ea.getScore());
			}
		}
		
		for (ExerciseAttempt ea:eaList) {
			if (ea.getScore()==max) {
				retVal.add(ea.getStudent());
			}
		}
		
		return retVal;
	}

	@Override
	public List<Student> findStudentsThatScoredMaxOnFirstAttemptAnyExercises() throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		ArrayList<Student> retVal=new ArrayList<Student>();
		
		List<ExerciseAttempt> exerciseList=factory.getExerciseAttempts().getAll();
		
		int maxScore=Integer.MIN_VALUE;
		for (ExerciseAttempt ex:exerciseList) {
			if (1==getExerciseAttemptNumber(ex)) {
				maxScore=Math.max(maxScore, ex.getScore());
			}
		}
		
		for (ExerciseAttempt ex:exerciseList) {
			if (1==getExerciseAttemptNumber(ex)) {
				if(maxScore==ex.getScore()) {
					retVal.add(ex.getStudent());
				}
			}
		}
		
		return retVal;
		
	}

	@Override
	public int findMaximumScore(Exercise exercise) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		int max=Integer.MIN_VALUE;
				
		List<ExerciseAttempt> eaList=factory.getExerciseAttempts().getExerciseAttemptsForExercise(exercise);
		for (ExerciseAttempt ea:eaList) {
			max=Math.max(max, ea.getScore());
		}
		
		if (max==Integer.MIN_VALUE) {
			max=0;
		}
		
		return max;
	}

	@Override
	public int findMinimumScore(Exercise exercise) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		int min=Integer.MAX_VALUE;
		
		
		List<ExerciseAttempt> eaList=factory.getExerciseAttempts().getExerciseAttemptsForExercise(exercise);
		for (ExerciseAttempt ea:eaList) {
			min=Math.min(min, ea.getScore());
		}
		
		if (min==Integer.MAX_VALUE) {
			min=0;
		}
		
		return min;
	}

	@Override
	public int findMaximumScore(Question question) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		int max=Integer.MIN_VALUE;
		
		
		List<ExerciseAttempt> eaList=factory.getExerciseAttempts().getAll();
		for (ExerciseAttempt ea:eaList) {
			for (AnswerAttempt q:ea.getAnswerAttempts()) {
				if (q.getQuestion()==question) {
					max=Math.max(q.getScore(), max);
				}
			}
		}
		
		if (Integer.MIN_VALUE == max) {
			max=0;
		}
		
		return max;
	}

	@Override
	public int findMinimumScore(Question question) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		int min=Integer.MAX_VALUE;
				
		List<ExerciseAttempt> eaList=factory.getExerciseAttempts().getAll();
		for (ExerciseAttempt ea:eaList) {
			for (AnswerAttempt q:ea.getAnswerAttempts()) {
				if (q.getQuestion()==question) {
					min=Math.min(q.getScore(), min);
				}
			}
		}
		
		if (Integer.MAX_VALUE == min) {
			min=0;
		}
		
		return min;
	}

	@Override
	public int findAverageAttempts(Exercise exercise) throws SQLException, ConnectionFailedException, RecordNotFoundException, RoleException {
		List<ExerciseAttempt> exList = factory.getExerciseAttempts().getExerciseAttemptsForExercise(exercise);
		int sum=0;
		int count=0;
		for (ExerciseAttempt ex:exList ) {
			count++;
			sum+=ex.getScore();
		}
		
		return (sum/count);
	}

	@Override
	public int findAverageAttempts(Question question) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private int getExerciseAttemptNumber(ExerciseAttempt ea) throws ConnectionFailedException {
		
		List<ExerciseAttempt> attempts=factory.getExerciseAttempts().getExerciseAttemptsForStudent(ea.getStudent());
		int attemptNumber=1;
		for (ExerciseAttempt a:attempts) {
			if (a.getExercise()==ea.getExercise()) {
				if (a!=ea) {
					if (a.getSubmissionDate().before(ea.getSubmissionDate())) {
						attemptNumber++;
					}
				}
			}
		}
		
		return attemptNumber;
	}

}

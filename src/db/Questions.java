package db;

import java.util.*;
import common.*;
import exception.*;

public interface Questions extends Tables{
	Question getQuestion(int id);
	List<Question> getQuestionsByExercise(Exercise exercise, boolean random) throws ConnectionFailedException, RecordNotFoundException;
	
	void putQuestion(Question question) throws ConnectionFailedException;
}
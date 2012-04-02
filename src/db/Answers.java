package db;

import java.util.*;

import common.*;
import exception.*;

public interface Answers extends Tables{
	public Answer getAnswer(int id) throws ConnectionFailedException, RecordNotFoundException;
	public List<Answer> getAnswersByQuestion(Question question) throws ConnectionFailedException, RecordNotFoundException;
	
	
	
}


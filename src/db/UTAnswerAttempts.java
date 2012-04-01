package db;

import java.util.ArrayList;
import java.util.List;

import common.AnswerAttempt;
import common.ExerciseAttempt;
import common.Question;
import exception.RecordNotFoundException;

public class UTAnswerAttempts implements AnswerAttempts {

    private List<AnswerAttempt> list = new ArrayList<AnswerAttempt>();
    
    private static UTAnswerAttempts instance=null;
    private UTAnswerAttempts() {
        
    }
    
    public static AnswerAttempts getInstance(Connection connection) {
        if (null==instance) {
            instance=new UTAnswerAttempts();
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
    public void addAnswerAttempt(AnswerAttempt answerAttempt) {
        list.add(answerAttempt);

    }

    @Override
    public AnswerAttempt getAnswerAttemptForExerciseAttemptAndQuestion(
            ExerciseAttempt exerciseAttempt, Question question) throws RecordNotFoundException
    {
       for (AnswerAttempt aa:list) {
    	   if ( (aa.getExerciseAttempt()==exerciseAttempt) &&
    			(aa.getQuestion()==question) ) {
    		   return aa;
    	   }
       }
       
       throw new RecordNotFoundException("No answer attempt found for given exercise and question");
    }

}

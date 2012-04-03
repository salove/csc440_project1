package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import action.ExerciseActions;
import action.ExerciseAttemptActions;
import common.*;



public class StudentDialogue extends GeneralDialogue {

	
	String[] menuItems = {
			"Complete an Exercise",
			"Review past Exercise attempts",
			"Exit"
	};
	
	
	
	StudentDialogue(UI ui) {
		super(ui);
	}
	
    public void showMainDialogue() {
    
    	Selection mainSelection=new Selection("Choose one of the following: ", menuItems);
    	for (boolean doExit=false; !doExit; )
    	switch(select(mainSelection)) {
    	case 0:
    		doExercise();
    		break;
    	case 1:
    		pastExercises();
    		break;
    	case 3:
    		doExit=true;
    		break;
    	}
    }
    
    private void doExercise() {
    	ui.clear();
    	ExerciseActions ea=new ExerciseActions(ui.getSession());
    	
    	try {
    		Student student=new Student(ui.getSession().getUser());
			List<Exercise> exList=ea.getForStudent(student);
			List<Exercise> exListValid=new ArrayList<Exercise>();
			C_Date now=new C_Date();
			for (Exercise ex:exList) {
				if ( ex.getEndDate().before(now)) {
					ExerciseAttemptActions eaa=new ExerciseAttemptActions(ui.getSession());
					List<ExerciseAttempt> priorAttempts=eaa.getExerciseAttempts(student);
					int countExerciseAttempts=0;
					for(ExerciseAttempt a:priorAttempts) {
						if (a.getExercise()==ex) {
							countExerciseAttempts++;
						}
					}
					if (countExerciseAttempts < ex.getRetriesAllowed()) {
						exListValid.add(ex);
					}
				}
			}
			
			if (exListValid.isEmpty()) {
				ui.write("There are no pending exercises.\n");
				ui.write("Press Enter to continue\n");
				return;
			}
			
			Exercise selectedExercise=selectExercise(exListValid);
			
			if (null!=selectedExercise) {
				
				ExerciseAttemptActions eaa=new ExerciseAttemptActions(ui.getSession());
				ExerciseAttempt attempt=eaa.create(student, selectedExercise);
				
				List<Question> questions=attempt.getQuestions();
				for (Question q:questions) {
					ui.write("\n\n");
					ui.write(q.getQuestionText());
					if (null!=q.getHintText()) {
						ui.write("\nHint:\n"+q.getHintText());
					}
					Vector<Answer> possibleAnswers=new Vector<Answer>(q.getIncorrectAnswers());
					Vector<Answer> correctAnswers=new Vector<Answer>(q.getCorrectAnswers());
					possibleAnswers.add(correctAnswers.get(Utils.random(0, correctAnswers.size()-1)));
					Vector<Answer> presentedAnswers=new Vector<Answer>();
					while (!possibleAnswers.isEmpty()) {
						presentedAnswers.add(possibleAnswers.remove(Utils.random(0, possibleAnswers.size()-1)));
					}
					Vector<String>answerText=new Vector<String>();
					for (Answer a:presentedAnswers) {
						answerText.add(a.getAnswerText());
					}
					Selection sel=new Selection("Choose one of the following answers:" , answerText);
					int selIdx=select(sel);
					Answer chosenAnswer=presentedAnswers.get(selIdx);
					ui.write("\n\nEnter Justification (optional):\n");
					String justification=ui.readLine();
					attempt.addAnswerAttempt(q, chosenAnswer, justification);
					ui.write("\n");
					if (chosenAnswer.isCorrect()) {
						ui.write("That answer is correct.");
					} else {
						ui.write("That answer is not correct.\n");
						
					}
					if (null!=chosenAnswer.getExplanation()) {
						ui.write(chosenAnswer.getExplanation());
						ui.write("\n");
						ui.write("Press enter to continue\n");
					}
					
					
				}
				eaa.storeExerciseAttempt(attempt);
			}
		} catch (Exception e) {
			ui.statusUpdate("Operation failed: "+e.getMessage());
		} 
    	
    }
    
    private void pastExercises() {
    	ExerciseAttemptActions eaa=new ExerciseAttemptActions(ui.getSession());
    	try {
			List<ExerciseAttempt> attempts=eaa.getExerciseAttempts(new Student(ui.getSession().getUser()));
			
			for (ExerciseAttempt ea:attempts) {
				ui.write(ea.getSubmissionDate().toString()+" "+ea.getExercise().getName()+" score="+ea.getScore());
				
			}
			ui.write("\n\nPress Enter to continue");
			ui.readLine();
		} catch (Exception e) {
			ui.statusUpdate("Display past exercises failed: "+e.getMessage());
		} 
    }
}

package ui;

import action.CourseActions;
import action.ExerciseActions;
import common.*;

public class TADialogue extends GeneralDialogue {
	
	
    TADialogue(UI ui) {
		super(ui);
		
	}

	public void showDialogue() {
        final String[] taMenu= {
        		"View Assigned Questions and Answers",
        		"Exit"
        };
        
        
        
        for (boolean doExit=false; !doExit; ) {
        	Selection taSelect=new Selection("Choose one of the following:", taMenu);
        
        	
        	switch(select(taSelect)) {
        	case 0:
        		viewAssigned();
        		break;
        	case 1:
        		doExit=true;
        		break;
        	}
        }
        
        
    }
	
	private void viewAssigned() {
		
		
		try {
			CourseActions ca=new CourseActions(ui.getSession());
			ExerciseActions ea=new ExerciseActions(ui.getSession());
			boolean foundSomething=false;
			
			
			ui.clear();
			for(Course course:ca.getAllCourses()) {
				for (TA ta:course.getTAList()) {
					if (ta.getUserId().equals(ui.getSession().getUser().getUserId())) {
						for (Exercise e:ea.getForCourse(course)) {
							for (Question q:e.getQuestions()) {
								foundSomething=true;
								ui.write("\n\nExercise "+formatExercise(e)+ " Question "+q.getId()+":");
								ui.write("\nQuestion Text:\n");
								ui.write(q.getQuestionText());
								ui.write("\nHint:\n");
								ui.write(q.getHintText());
								ui.write("\nCorrect answer(s)\n");
								for (Answer a:q.getCorrectAnswers()) {
									ui.write(a.getAnswerText());
									ui.write("\n");
									ui.write("Explanation:"+a.getExplanation()+"\n");
									
								}
								ui.write("\nIncorrect answer(s)\n");
								for (Answer a:q.getIncorrectAnswers()) {
									ui.write(a.getAnswerText());
									ui.write("\n");
									ui.write("Explanation:"+a.getExplanation()+"\n");
								}
							}
						}
					}
				}
			}
			
			if (!foundSomething) {
				ui.write("There are no assigned exercises for your TA classes.\n");
			}
			
			ui.write("\n\n");
			ui.write("Press enter to continue\n");
			ui.readLine();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			ui.statusUpdate("View assignments failed");
		}
		
		
	}
}

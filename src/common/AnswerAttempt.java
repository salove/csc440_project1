package common;

public class AnswerAttempt {

    private Question question;
    private Answer answer;
    private String justification=null;
    private int score;
    
    private ExerciseAttempt exerciseAttempt;
    
    
   
    
    public AnswerAttempt(ExerciseAttempt exerciseAttempt, Question question, Answer answer, String justification) {
        this.exerciseAttempt=exerciseAttempt;
        this.question=question;
        this.answer=answer;
        this.justification=justification;
        score= answer.isCorrect() ? exerciseAttempt.getExercise().getPointsPerCorrectAnswer() :
            exerciseAttempt.getExercise().getPointsPerWrongAnswer();
    }
    
    public Question getQuestion() {
        return question;
        
    }
    
    public Answer getAnswer() {
        return answer;
    }
    
    public String getJustification() {
        return justification;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int s) {
        score=s;
    }
    
    public ExerciseAttempt getExerciseAttempt() {
        return exerciseAttempt;
    }
    
}

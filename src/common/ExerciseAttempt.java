package common;

import java.util.Vector;

public class ExerciseAttempt {

    private Course course;
    private Student student;
    private Vector<Question> questions=new Vector<Question>();
    private C_Date startDate=new C_Date();
    private C_Date submissionDate=null;
    private Exercise exercise;
    private Vector<AnswerAttempt> answerAttempts=new Vector<AnswerAttempt>();
    
    public ExerciseAttempt(Course course, Student student, C_Date startDate, Exercise exercise) {
        this.course=course;
        this.student=student;
        this.startDate=startDate;
        this.exercise=exercise;
        
        Vector<Question> vectorOfQuestions = new Vector<Question>(exercise.getQuestions());
        
        // Randomize order of questions
        while (vectorOfQuestions.size()>0) {
            int questionIndex=Utils.random(0, vectorOfQuestions.size()-1);
            questions.add(vectorOfQuestions.remove(questionIndex));
        }
    }
    
    public void addAnswerAttempt(Question question, Answer answer, String justification) {
        answerAttempts.add(new AnswerAttempt(this,question,answer,justification));
    }
    
    public Course getCourse() {
        return course;
    }
    
    public Student getStudent() {
        return student;
        
    }
    
    @SuppressWarnings("unchecked")
    public Vector<Question> getQuestions() {
        return ((Vector<Question>)questions.clone());
    }
    
    public C_Date getStartDate() {
        return startDate;
    }
    
    public C_Date getSubmissionDate() {
        return submissionDate;
    }
    
    public Exercise getExercise() {
        return exercise;
    }
    
    public void setSubmissionDate(C_Date date) {
        this.submissionDate=date;
    }
    
    @SuppressWarnings("unchecked")
    public Vector<AnswerAttempt> getAnswerAttempts() {
        return ((Vector<AnswerAttempt>)answerAttempts.clone());
    }
}

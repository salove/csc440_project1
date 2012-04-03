package ui;

import java.util.List;
import java.util.Vector;

import common.*;

public class GeneralDialogue {
	
	protected UI ui;
	
	GeneralDialogue(UI ui) {
		this.ui=ui;
	}

	protected class Selection {
		Vector<String>selections=new Vector<String>();
		String headMsg=null;
		
		Selection(String headMsg, String[] text ) {
			for (String s:text) {
				selections.add(s);
			}
			this.headMsg=headMsg;
		}
		
		Selection(String headMsg, Vector<String>selections ) {
			this.selections=selections;
		}
		
		Selection() {
			// empty constructor
		}
	}
	
	int select(Selection s) {

		boolean isValid=false; 
		int retVal=-1;

		while (!isValid) {
			ui.clear();
			
			if (null!=s.headMsg) {
				ui.write("\n"+s.headMsg+"\n");
			} else {
				ui.write("\n\n");
			}

			for (int i=0; i<s.selections.size(); i++) {
				ui.write(" "+(i+1)+". "+s.selections.get(i)+"\n");
			}
			
			ui.write("\n Enter Selection: ");
			String rsp=ui.readLine();
			int iRsp=Utils.str2int(rsp)-1;
			if ( (iRsp>=0) && (iRsp<s.selections.size())) {
				retVal=iRsp;
				isValid=true;
			}
		}

		return retVal;
	}
	
	boolean yesNo() {
		ui.write(" [Enter yes or no] ");
		
		while (true) {
			String yn=ui.readLine();
			if ( yn.equalsIgnoreCase("yes") ||
					yn.equalsIgnoreCase("y") ) {
				return true;
			} else if ( yn.equalsIgnoreCase("no") ||
					yn.equalsIgnoreCase("n") ) {
				return false;
			}
		}
		
	}
	
	C_Date askDate() {
		ui.write(" [mm-dd-yyyy] ");
		
		while (true) {
			String rsp=ui.readLine();
			
			rsp=rsp.replaceAll("/", "-");
			String[] rspSplit=rsp.split("-");
			if (3==rspSplit.length) {
				int month=Utils.str2int(rspSplit[0]);
				int day=Utils.str2int(rspSplit[1]);
				int year=Utils.str2int(rspSplit[2]);
				
				int daysInMonth[] = { 29, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
				int monthIdx=month;
				if (monthIdx==2) {
					if ( (0==year%400) ||
						((0==year%4)&&(0!=year%100)) ) {
						monthIdx=0;
					}
				}
				
				
				if ( (month>=1) && (month<=12) &&
					 (day>=1) && (day<=daysInMonth[monthIdx]) &&
					 (year>=1800) && (year<=9999) ) {
					return new C_Date(month,day,year);
				} else {
					ui.write("\n [mm-dd-yyyy] ");
				}
			} else {
				ui.write(" [mm-dd-yyyy] ");
			}
			
		}
	}
	
	protected String formatUser(User s) {
		return (s.getUserId()+" ("+s.getName()+")");
	}
	
	protected String formatCourseSubject(CourseSubject s) {
		return (s.getIdCode()+"-"+s.getName());
	}
	
	protected String formatCourse(Course c) {
		return (c.getSubject().getIdCode()+"-"+c.getToken()+"-"+c.getName());
	}
	
	protected String formatExercise(Exercise ex) {
		return (ex.getCourse().getToken()+"-"+ex.getId());
	}
	
	protected Course selectCourse(List<Course>list) {
		Vector<Course> courseVector=new Vector<Course>(list);
		Vector<String> courseStrings=new Vector<String>();
		for (Course c:courseVector) {
			courseStrings.add(formatCourse(c));
		}
		
		ui.clear();
		Selection temp=new Selection("\nChoose one of the following courses:", courseStrings);
		int selectIdx=select(temp);
		
		return courseVector.get(selectIdx);
	}
	
	protected Exercise selectExercise(List<Exercise> list) {
		Vector<Exercise> exVector=new Vector<Exercise>(list);
		Vector<String> exStrings=new Vector<String>();
		for (Exercise ex:exVector) {
			exStrings.add(formatExercise(ex));
		}
		
		ui.clear();
		Selection temp=new Selection("\nChoose one of the following courses:", exStrings);
		int selectIdx=select(temp);
		
		return exVector.get(selectIdx);
	}
	
	protected CourseSubject selectCourseSubject(List<CourseSubject>list) {
		Vector<CourseSubject> subjectVector=new Vector<CourseSubject>(list);
		Vector<String> courseSubjectStrings=new Vector<String>();
		for (CourseSubject c:subjectVector) {
			courseSubjectStrings.add(formatCourseSubject(c));
		}
		
		ui.clear();
		Selection temp=new Selection("\nChoose one of the following course subjects:", courseSubjectStrings);
		int selectIdx=select(temp);
		
		return subjectVector.get(selectIdx);
	}
	
	protected User selectUser(List<User> list) {
		Vector<User> userVector=new Vector<User>(list);
		Vector<String> userString=new Vector<String>();
		for (User u:userVector) {
			userString.add(formatUser(u));
		}
		ui.clear();
		Selection temp=new Selection("\nChoose one of the following users:", userString);
		int selectIdx=select(temp);
		ui.write("TEST");
		ui.readLine();
		
		return userVector.get(selectIdx);
	}
}

package ui;

import java.util.Calendar;
import java.util.Vector;

import common.C_Date;
import common.Utils;

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

			for (int i=1; i<s.selections.size(); i++) {
				ui.write(" "+i+". "+s.selections.elementAt(i-1)+"\n");
			}
			
			ui.write(" Enter Selection: ");
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
				}
			}
			
		}
	}
}

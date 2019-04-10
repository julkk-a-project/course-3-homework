package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DateHandler {
	
	static List<String> dateList1 = new ArrayList<String>();
	static List<String> dateList2 = new ArrayList<String>();
	
	
	
	//checks if input date needs to be handled (if input illogical)
	public static boolean needsHandling() {
		
		try {
			if (getStartDate().equals(null) || getEndDate().equals(null)) {
				main.Main.window.textArea.append("****\nYou need to choose a date.\nTry again\n****\n");
				main.Main.window.errorTextArea.setText("Date read incorrectly");
				return false;
			}
			
			if (!isLegit(getStartDate()) || !isLegit(getEndDate())) {
				String tempS1 = "";
				
				if (!isLegit(getStartDate())) {
					tempS1 += "'start date'\n";
				}
				
				if (!isLegit(getEndDate())) {
					tempS1 += "'end date'\n";
				}
				
				main.Main.window.textArea.append("****\nYou need to retype the following:\n" + tempS1 + "...if you want to limit the dataset\n****\n");
				main.Main.window.errorTextArea.setText("Date read incorrectly");
				return false;
				
			} else if (isAfter(getStartDate(), getEndDate())) {
				main.Main.window.textArea.append("****\nYou can't choose a start date that's before end date.\nTry again\n****\n");
				main.Main.window.errorTextArea.setText("Date read incorrectly");
				return false;
			}
			main.Main.window.errorTextArea.setText("Date read correctly");
			return true;
	
		}catch(Exception e) {
			System.out.println("(In DateHandler) Something bad: "+e);
			main.Main.window.errorTextArea.setText("Date read incorrectly");
			return false;
		}
				
		
		/*
		String before = getStartDate();
		String after = getEndDate();
		try {
			String[] beforeArray = before.split("-");
			String[] afterArray = after.split("-");
			int[] b4 = new int[3];
			int[] aft = new int[3];
			for(int i = 0; i <= 3; i++) {
				b4[i] = Integer.parseInt(beforeArray[i]);
				aft[i] = Integer.parseInt(afterArray[i]);
			}
			String[] beforeArray2 = new String[3];
			String[] afterArray2 = new String[3];
			for(int i = 0; i <= 3; i++) {
				beforeArray2[i] = Integer.toString(b4[i]);
				
				afterArray2[i] = Integer.toString(aft[i]);
			}
			boolean bool1 = beforeArray2.equals(beforeArray);
			boolean bool2 = afterArray2.equals(afterArray);			
			
			
			return bool1 && bool2;
		}catch(Exception e) {
			return false;
		}	*/
	}
	
	
	
	
	//splits input dates and compares if input is logical
	private static boolean isAfter(String before, String after) {
		
		//format: 2019-12-31
		String[] beforeArray = before.split("-");
		String[] afterArray = after.split("-");
		
		int[] b4 = new int[3];
		int[] aft = new int[3];
		
		for(int i = 0; i < 3; i++) {
			
			b4[i] = Integer.parseInt(beforeArray[i]);
			aft[i] = Integer.parseInt(afterArray[i]);
		}

		
		//compares two dates. first the year, then the month, then day.
		if(b4[0] <= aft[0]) {
			if(b4[0] < aft[0] || b4[1] <= aft[1]) {
				if(b4[0] < aft[0] || b4[1] < aft[1] || b4[2] <= aft[2]) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	//checks that date is legit
	private static boolean isLegit(String date) {
		//format: 2019-12-31

		try {
			String[] dateArray = date.split("-");
			int[] d8 = new int[3];
			for(int i = 0; i < 3; i++) {
				d8[i] = Integer.parseInt(dateArray[i]);
			}

			//compares a date. first the year, then the month, then day.
			if((d8[0]/1000) > 0 && (d8[0]/1000) < 11) {
				if(d8[1] > 0 && d8[1] <= 12) {
					if(d8[2] > 0 && d8[2] <= 31) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			
			System.out.println("(In DateHandler) for "+date+" "+e);
			return false;
		}	
	}
	
	
	
	//if dates in range
	public static boolean inRange(String date) {
		boolean after = isAfter(date, getStartDate());
		boolean before = isAfter(getEndDate(), date);
		return before && after;
	}
	
	
	
	//to get end date from endDateTextArea
	private static String getEndDate() {
		return main.Main.window.endDateTextArea.getText();
	}

	

	//to get end date from startDateTextArea
	private static String getStartDate() {
		return main.Main.window.startDateTextArea.getText();
	}
	
	
	
	//sets dates
	public static void setDateList(boolean isSymbol2, List list) {
		if(isSymbol2) {
			dateList2 = list;
	
		} else {
			dateList1 = list;
		}
	}
	
}

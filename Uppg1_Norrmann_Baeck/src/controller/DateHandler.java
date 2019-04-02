package controller;

import java.util.Arrays;

public class DateHandler {
	
	
	
	public static boolean needsHandling() {
		
		//System.out.println("(In DateHandler)maamdiin");
		
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
	
	private static boolean isAfter(String before, String after) {
		
		//format: 2019-12-31
		
		String[] beforeArray = before.split("-");
		//System.out.println("(In DateHandler) BeforeArray: " + Arrays.toString(beforeArray));
		
		String[] afterArray = after.split("-");
		//System.out.println("(In DateHandler) AfterArray: " + Arrays.toString(afterArray));
		
		int[] b4 = new int[3];
		int[] aft = new int[3];
		
		for(int i = 0; i < 3; i++) {
			
			b4[i] = Integer.parseInt(beforeArray[i]);
			aft[i] = Integer.parseInt(afterArray[i]);
		}

		//System.out.println("(In DateHandler) B4: " + Arrays.toString(b4));
		//System.out.println("(In DateHandler) Aft: " + Arrays.toString(aft));
		
		//compares two dates. first the year, then the month, then day.
		
		if(b4[0] <= aft[0]) {
			if(b4[0] < aft[0] || b4[1] <= aft[1]) {
				if(b4[0] < aft[0] || b4[1] < aft[1] || b4[2] <= aft[2]) {
					//System.out.println("(In DateHandler) Returns false");
					return false;
				}
			}
		}
		//System.out.println("(In DateHandler) Returns true");
		return true;
	}
	
	
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
	
	
	
	public static boolean inRange(String date) {
		//System.out.println("(In DateHandler) inRange");
		boolean after = isAfter(date, getStartDate());
		boolean before = isAfter(getEndDate(), date);
		return before && after;
	}

	private static String getEndDate() {
		return main.Main.window.endDateTextArea.getText();
	}

	private static String getStartDate() {
		return main.Main.window.startDateTextArea.getText();
	}

}

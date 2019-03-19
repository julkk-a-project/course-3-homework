package controller;

public class DateHandler {
	
	public static boolean needsHandling() {
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
		}	
	}
	
	private static boolean isAfter(String before, String after) {
		
		//format: 2019-12-31
		
		String[] beforeArray = before.split("-");
		String[] afterArray = after.split("-");
		int[] b4 = new int[3];
		int[] aft = new int[3];
		for(int i = 0; i <= 3; i++) {
			b4[i] = Integer.parseInt(beforeArray[i]);
			aft[i] = Integer.parseInt(afterArray[i]);
		}
		
		//compares two dates. first the year, then the month, then day.
		if(b4[0] <= aft[0]) {
			if(b4[1] <= aft[1]) {
				if(b4[2] <= aft[2]) {
					return true;
				}
			}
			
		}
		return false;
	}
	
	public static boolean inRange(String date) {
		System.out.println("inRange");
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

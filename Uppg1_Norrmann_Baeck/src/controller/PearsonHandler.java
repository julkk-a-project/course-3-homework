package controller;

import java.util.List;


public class PearsonHandler {


	public static void pearsonCalculator() {
		List<Double> list1 = main.Main.dataStorer.getSymbol1Datapoints();
		List<Double> list2 = main.Main.dataStorer.getSymbol2Datapoints();

		System.out.println("1: "+list1);
		System.out.println("2: "+list2);

		if(list2.size() > list1.size()) {
			List<Double> temp = list1;
			list1 = list2;
			list2 = temp;	
		}

		System.out.println("1: "+list1.size());
		System.out.println("2: "+list2.size());
		
		int sizeDifference = (list1.size()) - (list2.size());
		list1.subList(sizeDifference, (list1.size() - 1)); //TODO: Check if sizeDifference - 1 is needed or if it should be only sizeDifference

		Double[] list1Array = (Double[]) list1.toArray(new Double[list1.size()]);
		Double[] list2Array = (Double[]) list2.toArray(new Double[list2.size()]);
		int n = list2Array.length;

		double pearson = calculateCov(list1Array, list2Array, n) / (calculateSD(list1Array)*calculateSD(list2Array));

		//TODO: put pearson into textArea
		main.Main.window.pearsonTextArea.setText(pearson + "");
		
		
	}


	public static double calculateSD(Double numArray[]) {
		double sum = 0.0, standardDeviation = 0.0;
		int length = numArray.length;

		for(double num : numArray) {
			sum += num;
		}

		double mean = sum/length;

		for(double num: numArray) {
			standardDeviation += Math.pow(num - mean, 2);
		}

		return Math.sqrt(standardDeviation/length);
	}

	//Function to find mean. 
	static double mean(Double[] list1, int n) { 
		double sum = 0; 

		for(int i = 0; i < n; i++) 
			sum = sum + list1[i]; 

		return sum / n; 
	} 

	//Function to find covariance. 
	static double calculateCov(Double[] list1, Double[] list2, int n) { 
		double sum = 0; 

		for(int i = 0; i < n; i++) 
			sum = sum + (list1[i] - mean(list1, n)) * 
			(list2[i] - mean(list2, n)); 
		return sum / (n - 1); 
	} 
}



package com.rushi.vehiclesurvey.util;

import java.text.DecimalFormat;

public class NumberUtil {

	public static int max(int number1, int number2) {
		if(number1 > number2) {
			return number1;
		}
		return number2;
	}
	
	public static double format(double number) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		return Double.parseDouble(decimalFormat.format(number));
	}
	
}

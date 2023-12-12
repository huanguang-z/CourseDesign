package com.pony.util;


public class FormatHelper {

	/**
	 * return a format time of 00:00
	 * @param milliseconds
	 * @return
	 */
	public static String formatDuration(int milliseconds){
		int seconds = milliseconds / 1000;
		int secondPart = seconds % 60;
		int minutePart = seconds / 60;
		return (minutePart >= 10 ? minutePart : "0" + minutePart) + ":" + (secondPart >= 10 ? secondPart : "0" + secondPart);
	}
	
	public static String formatTitle(String title, int length){
		int len = title.length() < length ? title.length():length;		
		String subString = title.substring(0, len);
		if(len < title.length()){
			subString += "...";
		}
		return subString;
	}
}

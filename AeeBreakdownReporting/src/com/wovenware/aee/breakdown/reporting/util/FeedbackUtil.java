package com.wovenware.aee.breakdown.reporting.util;

public class FeedbackUtil {
	private FeedbackUtil() {}
	
	public static String formatGeneralFeedback(String alertType, String title, String message) {
		return formatGeneralFeedback(alertType, title, message, true);
	}
	
	public static String formatGeneralFeedback(String alertType, String title, String message, boolean isCloseable) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("<div class=\"alert " + alertType + "\">");
		
		if(isCloseable) {
			stringBuilder.append("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>");
		}
		
		stringBuilder.append("<h4>" + title + "</h4>");
		stringBuilder.append(message);
		stringBuilder.append("</div>");
			
		return stringBuilder.toString();
	}
	
	public static String formatFieldFeedback(String message) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("<i class=\"icon-warning-sign\"></i>");
		stringBuilder.append("&nbsp;");
		stringBuilder.append(message);
			
		return stringBuilder.toString();
	}
}

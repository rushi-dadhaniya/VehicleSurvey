package com.rushi.vehilcesurvey.util;

import java.util.Calendar;
import java.util.TimeZone;
import com.rushi.vehiclesurvey.vo.Date;

public class TimeUtil {

	private static final double MILLIS_HOUR_FACTOR = 3600000.0;
	
	public Date convertMilliSecondsToDate(Long previousMillis, Long currentMillis, int currentDay) {
		
		if(previousMillis != null && currentMillis != null) {
			Date date = new Date();
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			calendar.setTimeInMillis(currentMillis);
			currentDay = isNewDay(previousMillis, currentMillis) ? currentDay + 1 : currentDay;
			date.setDay(currentDay);
			date.setHours(calendar.get(Calendar.HOUR_OF_DAY));
			date.setMinutes(calendar.get(Calendar.MINUTE));
			date.setSeconds(calendar.get(Calendar.SECOND));
			return date;
		}
		return null;
	}

	public Double convertMilliSecondsToHours(Long millis) {
		if(millis != null) {
			return millis / MILLIS_HOUR_FACTOR;
		}
		return null;
	}
	
	public boolean isNewDay(Long previousTime, Long currentTime) {
		return previousTime != null | currentTime != null ? currentTime < previousTime : false;
	}
}
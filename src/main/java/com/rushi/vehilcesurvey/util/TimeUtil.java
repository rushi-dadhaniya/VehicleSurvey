package com.rushi.vehilcesurvey.util;

import java.util.Calendar;
import java.util.TimeZone;
import com.rushi.vehiclesurvey.vo.Date;

public class TimeUtil {

	public Date convertMilliSecondsToDate(Long previousMillis, Long currentMillis, int currentDay) {
		
		if(previousMillis != null && currentMillis != null) {
			Date date = new Date();
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			calendar.setTimeInMillis(currentMillis);
			currentDay = currentMillis < previousMillis ? currentDay + 1 : currentDay;
			date.setDay(currentDay);
			date.setHours(calendar.get(Calendar.HOUR_OF_DAY));
			date.setMinutes(calendar.get(Calendar.MINUTE));
			date.setSeconds(calendar.get(Calendar.SECOND));
			return date;
		}
		return null;
	}

}
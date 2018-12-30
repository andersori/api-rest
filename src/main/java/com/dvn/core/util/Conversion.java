package com.dvn.core.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Conversion {
	public static LocalDateTime timestampToLocalDate(Timestamp ts) {
		if(ts != null) {
			return ts.toLocalDateTime();
		}else {
			return LocalDateTime.of(LocalDate.of(1, 1, 1), LocalTime.of(0, 0, 0));
		}
	}
	
	public static Timestamp localDateToTimestamp(LocalDateTime la) {
		if(la != null) {
			return Timestamp.valueOf(la);
		}else {
			return Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1, 1, 1), LocalTime.of(0, 0, 0)));
		}
	}
}
